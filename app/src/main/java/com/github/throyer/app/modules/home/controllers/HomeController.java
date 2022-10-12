package com.github.throyer.app.modules.home.controllers;

import static com.github.throyer.app.modules.shared.utils.Templates.INDEX;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

  @ModelAttribute
  public void addAttributes(Model model) {
    model.addAttribute("home", "active");
  }

  @RequestMapping("/")
  public String Index() {
    return INDEX;
  }
}
