package com.github.throyer.app.modules.users.entities;

import static java.time.LocalDateTime.now;
import static java.util.Optional.ofNullable;
import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.NONE;
import static com.github.throyer.app.modules.infra.environments.SecurityEnvironments.ENCODER;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;

import com.github.throyer.app.domain.models.Role;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements Serializable {
  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Getter(value = NONE)
  @Column(name = "active")
  private Boolean active = true;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt = now();

  @Column(name = "updated_at", nullable = true)
  private LocalDateTime updatedAt;

  @ManyToMany(cascade = DETACH, fetch = LAZY)
  @JoinTable(name = "user_role",
    joinColumns = { @JoinColumn(name = "user_id") }, 
    inverseJoinColumns = { @JoinColumn(name = "role_id") }
  )
  private List<Role> roles;

  public Boolean isActive() {
    return active;
  }

  public List<String> getAuthorities() {
    return ofNullable(roles)
      .map(roles -> roles
        .stream()
        .map(Role::getAuthority)
        .toList())
      .orElseGet(() -> List.of());
  }

  @PrePersist
  private void save() {
    this.password = ENCODER.encode(this.password);
  }
}
