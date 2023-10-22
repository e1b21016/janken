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

  @GetMapping("/fight")
  public String jankengame(@RequestParam String player, @RequestParam int id,
      ModelMap model) {
    Match match = new Match();
    String cpu = "pa";
    String result = "";

    switch (player) {
      case "pa":
        result = "Draw";
        break;
      case "choki":
        result = "You Win";
      case "gu":
        result = "You Lose";
        break;
    }
    match.setuser1(userMapper.selectAllByuserName(this.loginuser).getId());
    match.setuser2(id);
    match.setuser1Hand(player);
    match.setuser2Hand(cpu);

    matchMapper.insertmatch(match);

    model.addAttribute("loginUser", loginuser);
    model.addAttribute("user", userMapper.selectById(id));
    model.addAttribute("player", player);
    model.addAttribute("cpu", cpu);
    model.addAttribute("result", result);
    return "match.html";
  }
}
