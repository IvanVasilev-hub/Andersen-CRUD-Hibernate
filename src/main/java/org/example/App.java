package org.example;

import org.example.entity.User;

public class App {
  public static void main(String[] args) {
    UsersCrud usersCrud = new UsersCrud();
    RolesCrud rolesCrud = new RolesCrud();
    usersCrud.insertUser(new User("Test", "User"));
//    User pitt = usersCrud.getUserById(2);
//    User norton = usersCrud.getUserById(3);
//    User fincher = usersCrud.getUserById(4);
//    usersCrud.addRole(pitt, "user");
//    usersCrud.addRole(norton, "user");
//    usersCrud.addRole(fincher, "user");
//    usersCrud.addRole(fincher, "manager");
    usersCrud.printAllUsers();
    rolesCrud.printAllRoles();
  }
}
