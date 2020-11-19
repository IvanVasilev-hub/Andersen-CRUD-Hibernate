package org.example;

import org.example.entity.Role;
import org.example.entity.User;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UsersCrud {

  private SessionFactory sessionFactory;

  public UsersCrud() {
    sessionFactory = HibernateUtil.getSessionFactory();
  }

  public void insertUser(User user) {
    User inBase = getUserByName(user.getFirstName(), user.getLastName());
    if (inBase == null) {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      session.save(user);
      session.getTransaction().commit();
      session.close();
    }
  }

  public void addRole(User user, String roleName) {
    Role role = new RolesCrud().getRole(roleName);
    if (role != null) {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      user.addRole(role);
      session.update(user);
      session.getTransaction().commit();
      session.close();
    }
  }

  public User getUserById(long id) {
    User user = null;
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List users = session.createQuery("FROM User where id = :id")
        .setParameter("id", id).list();
    if (users != null && !users.isEmpty()) {
      user = (User) users.get(0);
    }
    session.getTransaction().commit();
    session.close();
    return user;
  }

  public User getUserByName(String firstName, String lastName) {
    User user = null;
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List users = session.createQuery("FROM User where firstName = :firstName AND lastName = :lastName")
        .setParameter("firstName", firstName)
        .setParameter("lastName", lastName).list();
    if (users != null && !users.isEmpty()) {
      user = (User) users.get(0);
    }
    session.getTransaction().commit();
    session.close();
    return user;
  }

  public void deleteUserById(long id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.createQuery("DELETE from User WHERE id = :id")
        .setParameter("id", id)
        .executeUpdate();
    session.getTransaction().commit();
    session.close();
  }

  public void deleteUser(User user) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.createQuery("DELETE from User where firstName = :firstName and lastName = :lastName")
        .setParameter("firstName", user.getFirstName())
        .setParameter("lastName", user.getLastName())
        .executeUpdate();
    session.getTransaction().commit();
    session.close();
  }

  public void updateUserById(long id, User user) {
    User currUser = getUserById(id);
    if (currUser != null) {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      currUser.setFirstName(user.getFirstName());
      currUser.setLastName(user.getLastName());
      currUser.setRoles(user.getRoles());
      session.update(currUser);
      session.getTransaction().commit();
      session.close();
    }
  }

  public void printAllUsers() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List users = session.createQuery("FROM User ").list();
    users.forEach(System.out::println);
    session.getTransaction().commit();
    session.close();
  }
}
