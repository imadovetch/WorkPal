package org.example.repositories.implementations.AdminTasks;

import org.example.Dao.Dao;

import java.util.HashMap;

public class PaymentRepository extends Dao {

    // Create a new payment method
    public int createPaymentMethod(String name) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", name);

        try {
            return insertData("PaymentMethods", data);
        } catch (Exception e) {
            System.out.println("Error creating payment method: " + e.getMessage());
            return -1; // Indicate failure
        }
    }

    // Delete a payment method
    public int deletePaymentMethod(String name) {
        try {
            return deleteData("PaymentMethods", "name", name);
        } catch (Exception e) {
            System.out.println("Error deleting payment method: " + e.getMessage());
            return -1; // Indicate failure
        }
    }
}
