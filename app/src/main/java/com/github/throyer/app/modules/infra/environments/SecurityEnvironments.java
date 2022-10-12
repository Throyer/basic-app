package com.github.throyer.app.modules.infra.environments;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityEnvironments {

  public static String HASHIDS_SECRET;
  public static String SESSION_COOKIE_NAME;
  public static String SESSION_COOKIE_SECRET;

  @Autowired
  public SecurityEnvironments(
    @Value("${hashid.secret}") String hashidSecret,
    @Value("${cookie.secret}") String cookieSecret,
    @Value("${server.servlet.session.cookie.name}") String sessionCookieName
  ) {
    SecurityEnvironments.HASHIDS_SECRET = hashidSecret;
    SecurityEnvironments.SESSION_COOKIE_SECRET = cookieSecret;
    SecurityEnvironments.SESSION_COOKIE_NAME = sessionCookieName;
  }
  
  public static Integer SESSION_COOKIE_EXPIRATION_TIME_IN_SECONDS = 86400;
  public static final Integer HASH_LENGTH = 10;
  public static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder(HASH_LENGTH);
  public static final Hashids HASH_ID = new Hashids(HASHIDS_SECRET, HASH_LENGTH);
}
