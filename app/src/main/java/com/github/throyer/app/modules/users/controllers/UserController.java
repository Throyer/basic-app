package com.github.throyer.app.modules.users.controllers;

import static com.github.throyer.app.modules.shared.utils.Templates.USER;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

  @ModelAttribute
  public void addAttributes(Model model) {
    model.addAttribute("user", "active");
  }

  @RequestMapping("/user")
  public String Index() {
    return USER;
  }
}
