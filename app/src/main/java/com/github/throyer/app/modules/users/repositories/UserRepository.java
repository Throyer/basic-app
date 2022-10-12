package com.github.throyer.app.modules.users.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.throyer.app.modules.users.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  public Optional<User> findOptionalByEmail(String email);
}
