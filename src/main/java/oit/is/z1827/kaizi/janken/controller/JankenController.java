package oit.is.z1827.kaizi.janken.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.security.Principal;

import oit.is.z1827.kaizi.janken.model.User;
import oit.is.z1827.kaizi.janken.model.Match;
import oit.is.z1827.kaizi.janken.model.Matchinfo;
import oit.is.z1827.kaizi.janken.model.MatchinfoMapper;
import oit.is.z1827.kaizi.janken.model.MatchMapper;
import oit.is.z1827.kaizi.janken.model.UserMapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.ui.ModelMap;

import oit.is.z1827.kaizi.janken.service.AsyncKekka;

@Controller
public class JankenController {

  private String loginuser;

  @Autowired
  UserMapper userMapper;

  @Autowired
  MatchMapper matchMapper;

  @Autowired
  MatchinfoMapper matchinfoMapper;

  @Autowired
  AsyncKekka kekka;

  @GetMapping("/janken")
  public String janken(Principal prin, ModelMap model) {
    ArrayList<User> user = userMapper.selectAllusers();
    ArrayList<Match> match = matchMapper.selectAllmatches();

    ArrayList<Matchinfo> matchinfoMappers = matchinfoMapper.selectAllmatchinfo();
    model.addAttribute("matchinfoMappers", matchinfoMappers);
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

  @Transactional
  @GetMapping("/fight")
  public String jankengame(@RequestParam int id, @RequestParam String player, Principal prin, ModelMap model) {
    // String cpu = "pa";
    // String result = "";

    // switch (player) {
    // case "pa":
    // result = "Draw";
    // break;
    // case "choki":
    // result = "You Win";
    // case "gu":
    // result = "You Lose";
    // break;
    // }
    String loginUser = prin.getName();
    model.addAttribute("loginUser", loginUser);
    User user1 = userMapper.selectAllByuserName(prin.getName());
    User users = userMapper.selectById(id);
    model.addAttribute("user1", user1);
    model.addAttribute("users", users);
    Matchinfo matchinfo = new Matchinfo();
    matchinfo.setuser1(user1.getId());
    matchinfo.setuser2(users.getId());
    matchinfo.setuser1Hand(player);
    matchinfo.setisActive(true);
    if (matchinfoMapper.selectuser1matchinfo(user1.getId()) == 0
        && matchinfoMapper.selectuser2matchinfo(user1.getId()) == 0) {
      matchinfoMapper.insertmatchinfo(matchinfo);
      model.addAttribute("matchinfo", matchinfo);
    } else {
      Matchinfo info = matchinfoMapper.selectusermatchinfo(user1.getId());
      Match match = new Match();
      match.setuser1(info.getUser1());
      match.setuser2(info.getuser2());
      match.setuser1Hand(info.getuser1Hand());
      match.setuser2Hand(player);
      match.setisActive(true);
      matchMapper.insertmatch(match);
      model.addAttribute("match", match);
      matchinfoMapper.updatebyisactive(info);
    }
    return "wait.html";
  }

  @GetMapping("active")
  public SseEmitter active() {
    final SseEmitter sseEmitter = new SseEmitter();
    this.kekka.asyncactivematches(sseEmitter);
    return sseEmitter;

  }

}
