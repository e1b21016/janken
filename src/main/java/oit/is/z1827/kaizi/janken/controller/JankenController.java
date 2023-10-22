package oit.is.z1827.kaizi.janken.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.security.Principal;

import oit.is.z1827.kaizi.janken.model.User;
import oit.is.z1827.kaizi.janken.model.Match;
import oit.is.z1827.kaizi.janken.model.MatchMapper;
import oit.is.z1827.kaizi.janken.model.UserMapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;

@Controller
public class JankenController {

  private String loginuser;

  @Autowired
  UserMapper userMapper;

  @Autowired
  MatchMapper matchMapper;

  @GetMapping("/janken")
  public String janken(Principal prin, ModelMap model) {
    ArrayList<User> user = userMapper.selectAllusers();
    ArrayList<Match> match = matchMapper.selectAllmatches();
    this.loginuser = prin.getName();
    model.addAttribute("loginUser", this.loginuser);
    model.addAttribute("user", user);
    model.addAttribute("match", match);
    return "janken.html";
  }

  @GetMapping("/match")
  public String match(@RequestParam int id, ModelMap model) {
    User user = userMapper.selectById(id);
    model.addAttribute("loginUser", this.loginuser);
    model.addAttribute("user", user);
    return "match.html";
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
    model.addAttribute("playerhand", playerhand);
    model.addAttribute("cpuhand", cpuhand);
    model.addAttribute("result", result);
    return "janken.html";
  }
}
