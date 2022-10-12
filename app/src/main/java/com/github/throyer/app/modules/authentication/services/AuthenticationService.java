package com.github.throyer.app.modules.authentication.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.throyer.app.modules.authentication.models.Authorized;
import com.github.throyer.app.modules.users.repositories.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {
  private final UserRepository repository;

  public AuthenticationService(UserRepository repository) {
    this.repository = repository;
  }
  
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    var user = repository.findByEmailFetchRoles(email)
      .orElseThrow(() -> new UsernameNotFoundException("Senha ou usuário invalido."));
    return new Authorized(user);
  }
}
