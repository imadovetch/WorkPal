package org.example.repositories.implementations;

import org.example.Dao.Dao;
import org.example.entities.User;

import java.util.HashMap;
import java.util.List;

public class UserRepositoryImp extends Dao {

    // Register a new user
    public void register(String email, String password, String phone) {
        // Check if the email is already registered
        List<HashMap<String, Object>> existingUserCheck = fetchData("users", "email", email);

        if (!existingUserCheck.isEmpty()) {
            System.out.println("Email is already registered.");
            return;
        }

        // Determine the role based on existing users
        List<HashMap<String, Object>> firstUserCheck = fetchData("users", null, null);
        String role;
        String table;

        if (firstUserCheck.isEmpty()) {
            role = "Admin";
            table = "admins";  // Insert the first user as an Admin
        } else {
            role = "Member";  // Default role for new users
            table = "members";  // Insert regular users into the 'members' table
        }

        // Prepare user data for insertion
        HashMap<String, Object> userData = new HashMap<>();
        userData.put("email", email);
        userData.put("password", password);
        userData.put("phone", phone);

        int result = insertData(table, userData);  // Insert into the appropriate table (admins, members)
        if (result > 0) {
            System.out.println("User registered successfully as " + role);
        } else {
            System.out.println("Failed to register the user.");
        }
    }

    // Login functionality
    public boolean login(String email, String password) {
        // Fetch the user based on the email
        List<HashMap<String, Object>> users = fetchData("users", "email", email);

        if (!users.isEmpty()) {
            HashMap<String, Object> user = users.get(0);  // Get the first user with matching email
            String storedPassword = (String) user.get("password");

            // Check if the password matches
            if (storedPassword.equals(password)) {
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
        } else {
            System.out.println("User not found.");
        }

        return false;
    }

    // Fetch and display all users
    public List<HashMap<String, Object>> fetchUsers() {
        List<HashMap<String, Object>> users = fetchData("users", null, null);

        if (!users.isEmpty()) {
            System.out.println("List of Users:");
            for (HashMap<String, Object> user : users) {
                System.out.println("ID: " + user.get("id") + ", Email: " + user.get("email") + ", Phone: " + user.get("phone") );
            }
        } else {
            System.out.println("No users found.");
        }
        return users;
    }

    public void updateUserRole(String email) {
        List<HashMap<String, Object>> member = fetchData("members", "email", email);
        List<HashMap<String, Object>> manager = fetchData("managers", "email", email);

        if (member.isEmpty() && manager.isEmpty()) {
            System.out.println("User not found.");
            return;
        }

        if (!member.isEmpty()) {
            HashMap<String, Object> user = member.get(0); // Get the user data

            // Remove from members and add to managers
            deleteData("members", "email", email);
            int result = insertData("managers", user);

            if (result > 0) {
                System.out.println("User promoted to Manager.");
            } else {
                System.out.println("Failed to promote user.");
            }
        } else if (!manager.isEmpty()) {
            HashMap<String, Object> user = manager.get(0); // Get the user data

            deleteData("managers", "email", email);
            int result = insertData("members", user);

            if (result > 0) {
                System.out.println("User demoted to Member.");
            } else {
                System.out.println("Failed to demote user.");
            }
        }
    }


    // Testing functionality
    public void runTests() {
//        // Register test users
//        register("admin@example.com", "admin123", "1234567890");
//        register("member1@example.com", "password1", "1234567891");
//        register("member2@example.com", "password2", "1234567892");
//
//        // Attempt login
//        System.out.println("Login test 1:");
         login("admin@example.com", "admin123");  // Should succeed
        login("member1@example.com", "wrongpassword");  // Should fail
//
//        // Fetch and display all users
//        System.out.println("Fetching users:");
//        fetchUsers();
//
//        // Update role test
        System.out.println("Updating user role for member1@example.com:");
      updateUserRole("member1@example.com");  // Promote to Manager
        fetchUsers();


    }
}
