package com.github.throyer.app.modules.infra.configuration;

import static com.github.throyer.app.modules.infra.constants.SecurityConstants.ACESSO_NEGADO_URL;
import static com.github.throyer.app.modules.infra.constants.SecurityConstants.HOME_URL;
import static com.github.throyer.app.modules.infra.constants.SecurityConstants.LOGIN_ERROR_URL;
import static com.github.throyer.app.modules.infra.constants.SecurityConstants.LOGIN_URL;
import static com.github.throyer.app.modules.infra.constants.SecurityConstants.LOGOUT_URL;
import static com.github.throyer.app.modules.infra.constants.SecurityConstants.PASSWORD_PARAMETER;
import static com.github.throyer.app.modules.infra.constants.SecurityConstants.USERNAME_PARAMETER;
import static com.github.throyer.app.modules.infra.environments.SecurityEnvironments.ENCODER;
import static com.github.throyer.app.modules.infra.environments.SecurityEnvironments.SESSION_COOKIE_EXPIRATION_TIME_IN_SECONDS;
import static com.github.throyer.app.modules.infra.environments.SecurityEnvironments.SESSION_COOKIE_NAME;
import static com.github.throyer.app.modules.infra.environments.SecurityEnvironments.SESSION_COOKIE_SECRET;
import static org.springframework.http.HttpMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.github.throyer.app.modules.authentication.services.AuthenticationService;

@Configuration
public class SecurityConfiguration {

  private final UserDetailsService userDetailsService;

  @Autowired
  public SecurityConfiguration(AuthenticationService authenticationService) {
    this.userDetailsService = authenticationService;
  }
    
  @Autowired
  protected void globalConfiguration(AuthenticationManagerBuilder authentication) throws Exception {
    authentication
      .userDetailsService(userDetailsService)
      .passwordEncoder(ENCODER);
  }

  @Bean
  protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http.
      authorizeRequests()
        .antMatchers(GET, LOGIN_URL, "/webjars/**", "/css/**", "/js/**")
          .permitAll()        
        .anyRequest()
          .authenticated()
      
      .and()
        .csrf()
          .disable()        
      .formLogin()
        .loginPage(LOGIN_URL)
          .failureUrl(LOGIN_ERROR_URL)
          .defaultSuccessUrl(HOME_URL)
            .usernameParameter(USERNAME_PARAMETER)
            .passwordParameter(PASSWORD_PARAMETER)

      .and()        
        .rememberMe()
          .rememberMeParameter("remember-me")
          .rememberMeCookieName("REMEMBER_ME")
            .key(SESSION_COOKIE_SECRET)
              .tokenValiditySeconds(SESSION_COOKIE_EXPIRATION_TIME_IN_SECONDS)

      .and()
        .logout()
          .deleteCookies(SESSION_COOKIE_NAME)
            .logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL))
              .logoutSuccessUrl(LOGIN_URL)
      
      .and()
        .exceptionHandling()     
          .accessDeniedPage(ACESSO_NEGADO_URL);

      return http.build();
  }
}
