package org.example.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(name = "users_roles",
      joinColumns = {@JoinColumn(name = "user_id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id")}
  )
  private Set<Role> roles = new HashSet<>();

  public User(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public boolean addRole(Role role) {
    return roles.add(role);
  }

  public boolean removeRole(Role role) {
    return roles.remove(role);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    return firstName.equals(user.firstName) && lastName.equals(user.lastName);
  }

  @Override
  public int hashCode() {
    return firstName.hashCode() + lastName.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("id: ").append(id)
        .append(" full name: ").append(firstName).append(" ").append(lastName);
    sb.append("\n\tRoles: ");
    if (!roles.isEmpty()) {
      roles.forEach(role -> sb.append(role.getRole()).append(" "));
    } else {
      sb.append("No roles assigned.");
    }
    return sb.toString();
  }
}
