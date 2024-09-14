package org.example.entities;

import java.util.List;

public class User {
    private int id;
    //private String name;
    private String email;
    private String phone;
    private String role;
    public static  User Main = new User(5,"","","");
    public static List<User> Users;

    public User(int id,  String email, String phone,  String role) {
        this.id = id;
      //  this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

}

