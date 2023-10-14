package oit.is.z1827.kaizi.janken.controller;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;

@Controller
public class JankenController {
  @GetMapping("/janken")
  public String janken() {
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

    if (playerhand == "pa") {
      result = "Draw";
    } else if (playerhand == "gu") {
      result = "You Win";
    } else if (playerhand == "choki") {
      result = "You Lose";
    }

    model.addAttribute("playerhand", playerhand);
    model.addAttribute("cpuhand", cpuhand);
    model.addAttribute("result", result);
    return "janken.html";
  }
}
