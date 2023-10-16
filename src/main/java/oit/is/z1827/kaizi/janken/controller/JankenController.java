package oit.is.z1827.kaizi.janken.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

import java.security.Principal;
import oit.is.z1827.kaizi.janken.model.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;

@Controller
public class JankenController {

  @Autowired
  private Entry entry;
  private String loginuser;

  @GetMapping("/janken")
  public String janken(Principal prin, ModelMap model) {
    this.loginuser = prin.getName();
    this.entry.addUser(loginuser);
    model.addAttribute("loginUser", this.loginuser);
    model.addAttribute("user", this.entry);
    return "janken.html";
  }

  @PostMapping("/janken")
  public String janken(@RequestParam String name, ModelMap model) {
    model.addAttribute("name", name);
    return "janken.html";
  }

  @GetMapping("/jankengame")
  public String pa(@RequestParam String playerhand, ModelMap model) {
    String result = "";
    String cpuhand = "pa";

    switch (playerhand) {
      case "pa":
        result = "Draw";
        break;
      case "choki":
        result = "You Win";
      case "gu":
        result = "You Lose";
        break;
    }

    model.addAttribute("loginUser", loginuser);
    model.addAttribute("user", this.entry.getUsers());
    model.addAttribute("playerhand", playerhand);
    model.addAttribute("cpuhand", cpuhand);
    model.addAttribute("result", result);
    return "janken.html";
  }
}
