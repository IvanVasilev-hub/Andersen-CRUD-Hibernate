package org.example.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id")
  private Integer id;

  @Column(name = "role")
  private String role;

  @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
  private Set<User> users = new HashSet<>();

  public Role(String role) {
    this.role = role.toLowerCase();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Role role = (Role) o;

    return id.equals(role.id) && this.role.equals(role.role);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("id: ").append(id)
        .append(", Role: ").append(role)
        .append("\n\tUsers: ");
    if (!users.isEmpty()) {
      users.forEach(user -> sb.append(user.getFirstName()).append(" ")
          .append(user.getLastName()).append("|"));
    } else {
      sb.append("No users assigned.");
    }
    return sb.toString();
  }
}
