package org.example;

import org.example.entity.Role;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class RolesCrud {
  private SessionFactory sessionFactory;

  public RolesCrud() {
    sessionFactory = HibernateUtil.getSessionFactory();
  }

  public void insertRole(Role role) {
    Role roleInBase = getRole(role.getRole());
    if (roleInBase == null) {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      session.save(role);
      session.getTransaction().commit();
      session.close();
    }
  }

  public Role getRole(String role) {
    Role result = null;
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List roles = session.createQuery("FROM Role where role = :role")
        .setParameter("role", role).list();
    if (roles != null && !roles.isEmpty()) {
      result = (Role) roles.get(0);
    }
    session.getTransaction().commit();
    session.close();
    return result;
  }

  public void deleteRole(Role role) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Query query = session.createQuery("DELETE FROM Role WHERE role = :role")
        .setParameter("role", role.getRole());
    query.executeUpdate();
    session.getTransaction().commit();
    session.close();
  }

  public void updateRole(String old, String role) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Query query = session.createQuery("UPDATE Role set role = :newRole WHERE role = :oldRole")
        .setParameter("newRole", role)
        .setParameter("oldRole", old);
    query.executeUpdate();
    session.getTransaction().commit();
    session.close();
  }

  public void printAllRoles() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List roles = session.createQuery("FROM Role ").list();
    roles.forEach(System.out::println);
    session.getTransaction().commit();
    session.close();
  }
}
