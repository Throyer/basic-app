package com.github.throyer.app.domain.models;

import static java.util.Optional.ofNullable;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "role")
@Table(name = "role")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role implements GrantedAuthority {
  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Override
  public String toString() {
    return ofNullable(name).orElse("");
  }

  @Override
  public String getAuthority() {
    return toString();
  }
}
