package org.example;

import org.example.GUI.MainGUI;
import org.example.repositories.implementations.UserRepositoryImp;
import org.example.services.implementations.MigrationServiceImp;

import java.util.HashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");


    try{
  // new MigrationServiceImp().Main();

     // new MainGUI();
       UserRepositoryImp userRepository = new UserRepositoryImp();
//
// userRepository.runTests();
     //   userRepository.login("jane@example.com", "password456");
//        boolean isLoggedIn = userRepository.login("john@example.com", "password123");
//        HashMap<String, Object> users = userRepository.fetchUsers();
//

    }catch(Exception e){

    }
    }
}