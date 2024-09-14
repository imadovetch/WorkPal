package org.example.repositories.implementations;

import org.example.Dao.Dao;
import org.example.entities.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class UserRepositoryImp extends Dao {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    public void register(String email, String password, String phone) {
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format.");
            return;
        }

        List<HashMap<String, Object>> existingUserCheck = fetchData("users", "email", email);

        if (!existingUserCheck.isEmpty()) {
            System.out.println("Email is already registered.");
            return;
        }

        List<HashMap<String, Object>> firstUserCheck = fetchData("users", null, null);
        String role;
        String table;

        if (firstUserCheck.isEmpty()) {
            role = "Admin";
            table = "admins";
        } else {
            role = "Member";
            table = "members";
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        HashMap<String, Object> userData = new HashMap<>();
        userData.put("email", email);
        userData.put("password", hashedPassword);
        userData.put("phone", phone);

        try {
            int result = insertData(table, userData);
            if (result > 0) {
                System.out.println("User registered successfully as " + role);
            } else {
                System.out.println("Failed to register the user.");
            }
        } catch (Exception e) {
            System.out.println("Error during registration: " + e.getMessage());
        }
    }

    public boolean login(String email, String password) {
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format.");
            return false;
        }

        List<HashMap<String, Object>> users = fetchData("users", "email", email);

        if (!users.isEmpty()) {
            HashMap<String, Object> user = users.get(0);
            String storedPassword = (String) user.get("password");

            try {
                if (BCrypt.checkpw(password, storedPassword)) {
                    System.out.println("Login successful.");
                    User.Main = new User(
                            (Integer) user.get("id"),
                            user.get("email").toString(),
                            user.get("phone").toString(),
                            ""
                    );
                    return true;
                } else {
                    System.out.println("Invalid password.");
                }
            } catch (Exception e) {
                System.out.println("Error during login: " + e.getMessage());
            }
        } else {
            System.out.println("User not found.");
        }

        return false;
    }

    public List<HashMap<String, Object>> fetchUsers() {
        try {
            List<HashMap<String, Object>> users = fetchData("users", null, null);
            if (!users.isEmpty()) {
                System.out.println("List of Users:");
                for (HashMap<String, Object> user : users) {
                    System.out.println("ID: " + user.get("id") + ", Email: " + user.get("email") + ", Phone: " + user.get("phone"));
                }
            } else {
                System.out.println("No users found.");
            }
            return users;
        } catch (Exception e) {
            System.out.println("Error fetching users: " + e.getMessage());
            return null;
        }
    }

    public void updateUserRole(String email) {
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format.");
            return;
        }

        try {
            List<HashMap<String, Object>> member = fetchData("members", "email", email);
            List<HashMap<String, Object>> manager = fetchData("managers", "email", email);

            if (member.isEmpty() && manager.isEmpty()) {
                System.out.println("User not found.");
                return;
            }

            if (!member.isEmpty()) {
                HashMap<String, Object> user = member.get(0);
                deleteData("members", "email", email);
                int result = insertData("managers", user);

                if (result > 0) {
                    System.out.println("User promoted to Manager.");
                } else {
                    System.out.println("Failed to promote user.");
                }
            } else if (!manager.isEmpty()) {
                HashMap<String, Object> user = manager.get(0);
                deleteData("managers", "email", email);
                int result = insertData("members", user);

                if (result > 0) {
                    System.out.println("User demoted to Member.");
                } else {
                    System.out.println("Failed to demote user.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error updating user role: " + e.getMessage());
        }
    }

    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public void runTests() {
        try {
            register("admin@example.com", "admin123", "1234567890");
            register("member1@example.com", "password1", "1234567891");
            register("member2@example.com", "password2", "1234567892");

            System.out.println("Login test 1:");
            login("admin@example.com", "admin123");
            login("member1@example.com", "wrongpassword");

            System.out.println("Fetching users:");
            fetchUsers();

            System.out.println("Updating user role for member1@example.com:");
            updateUserRole("member1@example.com");
            fetchUsers();
        } catch (Exception e) {
            System.out.println("Error during tests: " + e.getMessage());
        }
    }
    public static void main(String[] args){
        new UserRepositoryImp().runTests();
    }
}
