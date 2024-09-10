package org.example.repositories.implementations.AdminTasks;

import org.example.Dao.Dao;

import java.util.HashMap;

public class PaymentRepository extends Dao {

    public int createPaymentMethod(String name) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", name);

        return insertData("PaymentMethods", data);
    }

    public int deletePaymentMethod(String name) {
        // Call the deleteData method from the Dao class
        return deleteData("PaymentMethods", "name", name);
    }
}
