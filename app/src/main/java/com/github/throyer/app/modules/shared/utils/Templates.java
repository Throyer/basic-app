package com.github.throyer.app.modules.shared.utils;

import static java.text.MessageFormat.format;

public class Templates {
  private static final String BASE_PATH = "views";
  public static final String LOGIN = format("{1}/{2}", BASE_PATH, "login");
  public static final String INDEX = format("{1}/{2}", BASE_PATH, "index");
  public static final String CONTACT = format("{1}/{2}", BASE_PATH, "contact");
  public static final String USER = format("{1}/{2}", BASE_PATH, "user");
}
