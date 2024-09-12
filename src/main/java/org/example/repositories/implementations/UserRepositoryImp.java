package org.example.repositories.implementations;

import org.example.Dao.Dao;
import org.example.entities.User;

import java.util.HashMap;
import java.util.List;

public class UserRepositoryImp extends Dao {

    // Register a new user
    public void register(String email, String password, String phone) {
        List<HashMap<String, Object>> existingUserCheck = fetchData("users", "email", email);

        if (!existingUserCheck.isEmpty()) {
            System.out.println("Email is already registered.");
            return;
        }

        List<HashMap<String, Object>> firstUserCheck = fetchData("users", null, null);
        String role = firstUserCheck.isEmpty() ? "Admin" : "Member";

        HashMap<String, Object> userData = new HashMap<>();
        userData.put("email", email);
        userData.put("password", password);
        userData.put("phone", phone);
        userData.put("role", role);

        int result = insertData("users", userData);
        if (result > 0) {
            System.out.println("User registered successfully as " + role);
        } else {
            System.out.println("Failed to register the user.");
        }
    }


    // Login functionality
    public boolean login(String email, String password) {
        List<HashMap<String, Object>> users = fetchData("users", "email", email);

        if (!users.isEmpty()) {
            HashMap<String, Object> user = users.get(0); // Get the first user with matching email
            String storedPassword = (String) user.get("password");

            if (storedPassword.equals(password)) {
                System.out.println("Login successful.");
                User.Main = new User(
                        (Integer) user.get("id"),
                        user.get("email").toString(),
                        user.get("phone").toString(),
                        user.get("role").toString()
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
                System.out.println("ID: " + user.get("id") + ", Email: " + user.get("email") + ", Phone: " + user.get("phone") + ", Role: " + user.get("role"));
            }
        } else {
            System.out.println("No users found.");
        }
        return users;
    }

    // Update user role
    public void updateUserRole(String email) {
        List<HashMap<String, Object>> users = fetchData("users", "email", email);

        if (!users.isEmpty()) {
            HashMap<String, Object> user = users.get(0); // Get the first user with matching email
            String currentRole = (String) user.get("role");
            String newRole = "Member".equals(currentRole) ? "Manager" : "Member";

            HashMap<String, Object> updateData = new HashMap<>();
            updateData.put("role", newRole);

            int result = updateData("users", updateData, "email", email);
            if (result > 0) {
                System.out.println("User role updated to " + newRole);
            } else {
                System.out.println("Failed to update user role.");
            }
        } else {
            System.out.println("User not found.");
        }
    }
}
