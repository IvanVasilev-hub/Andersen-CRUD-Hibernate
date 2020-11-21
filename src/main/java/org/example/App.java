package org.example;

public class App {
  public static void main(String[] args) {
    UsersCrud usersCrud = new UsersCrud();
    RolesCrud rolesCrud = new RolesCrud();
//    usersCrud.insertUser(new User("Test", "User"));
//    User pitt = usersCrud.getUser(2);
//    System.out.println(pitt);
//    User norton = usersCrud.getUserById(3);
//    User fincher = usersCrud.getUserById(4);
//    usersCrud.addRole(pitt, "user");
//    usersCrud.addRole(norton, "user");
//    usersCrud.addRole(fincher, "user");
//    usersCrud.addRole(fincher, "manager");
//    System.out.println(usersCrud.getUser("Brad", "Pitt"));
    System.out.println(rolesCrud.getRole(1));
//    usersCrud.printAllUsers();
//    rolesCrud.printAllRoles();
  }
}
