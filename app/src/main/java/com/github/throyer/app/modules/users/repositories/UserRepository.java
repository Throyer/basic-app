package com.github.throyer.app.modules.users.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.throyer.app.modules.users.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  @Query("select user from users user join fetch user.roles where user.email = :email")
  public Optional<User> findByEmailFetchRoles(@Param("email") String email);
}
