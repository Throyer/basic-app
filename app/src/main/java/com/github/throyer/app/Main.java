package com.github.throyer.app;

import static java.lang.System.setProperty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
  public static void main(String... args) {
    setProperty("org.jooq.no-logo", "true");
    SpringApplication.run(Main.class, args);
  }
}
