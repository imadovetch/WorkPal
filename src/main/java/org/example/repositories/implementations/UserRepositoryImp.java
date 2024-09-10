package org.example.repositories.implementations;

import org.example.Dao.Dao;

import java.util.HashMap;

public class UserRepositoryImp extends Dao {

    public void register(String email, String password, String phone) {
        HashMap<String, Object> firstUserCheck = fetchData("users", null, null);

        String role;
        if (firstUserCheck.isEmpty()) {
            role = "Admin";
        } else {
            role = "Member";
        }

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

    public boolean login(String email, String password) {
        HashMap<String, Object> user = fetchData("users", "email", email);

        if (!user.isEmpty()) {
            String storedPassword = (String) user.get("password");

            if (storedPassword.equals(password)) {
                System.out.println("Login successful.");
                return true;
            } else {
                System.out.println("Invalid password.");
            }
        } else {
            System.out.println("User not found.");
        }

        return false;
    }

    public HashMap<String, Object> fetchUsers() {
        HashMap<String, Object> users = fetchData("users", null, null);

        if (!users.isEmpty()) {
            System.out.println("List of Users:");
            users.forEach((key, value) -> {
                System.out.println(key + ": " + value);
            });
        } else {
            System.out.println("No users found.");
        }
        return users;
    }

    public void updateUserRole(String email) {
        HashMap<String, Object> user = fetchData("users", "email", email);

        if (!user.isEmpty()) {
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
