package oit.is.z1827.kaizi.janken.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z1827.kaizi.janken.model.Entry;

/**
 * /sample3へのリクエストを扱うクラス authenticateの設定をしていれば， /sample3へのアクセスはすべて認証が必要になる
 */
@Controller
@RequestMapping("/sample3")
public class JankenController {

  @Autowired
  private Entry entry;

  @GetMapping("step7")
  public String sample37() {
    return "sample37.html";
  }

  /**
   *
   * @param model Thymeleafにわたすデータを保持するオブジェクト
   * @param prin  ログインユーザ情報が保持されるオブジェクト
   * @return
   */

  @GetMapping("/janken")
  public String janken(Principal prin, ModelMap model) {
    this.loginUser = prin.getName();
    this.entry.addUser(loginUser);
    model.addAttribute("room", this.room);

    return "sample37.html";
  }

  @GetMapping("step9")
  public String sample39(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    Room newRoom = new Room();
    newRoom.addUser(loginUser);
    model.addAttribute("new_room", newRoom);

    return "sample37.html";
  }

}
