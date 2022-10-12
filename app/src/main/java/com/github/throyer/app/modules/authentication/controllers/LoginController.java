package com.github.throyer.app.modules.authentication.controllers;

import static com.github.throyer.app.modules.shared.utils.Templates.LOGIN;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {
  @ModelAttribute
  public void addAttributes(Model model) {
    model.addAttribute("login", "active");
  }

  @GetMapping("/login")
  public String login() {
    return LOGIN;
  }
}
