package com.github.throyer.app.modules.contact.controllers;

import static com.github.throyer.app.modules.shared.utils.Templates.CONTACT;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {

  @ModelAttribute
  public void addAttributes(Model model) {
    model.addAttribute("contatc", "active");
  }

  @RequestMapping("/contatc")
  public String Index() {
    return CONTACT;
  }
}
