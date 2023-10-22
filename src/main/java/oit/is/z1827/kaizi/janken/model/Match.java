package oit.is.z1827.kaizi.janken.model;

public class Match {
  int id, user1, user2;
  String user1Hand, user2Hand;

  public int getUser1() {
    return user1;
  }

  public void setuser1(int user1) {
    this.user1 = user1;
  }

  public int getuser2() {
    return user2;
  }

  public void setuser2(int user2) {
    this.user2 = user2;
  }

  public String getuser1Hand() {
    return user1Hand;
  }

  public void setuser1Hand(String user1Hand) {
    this.user1Hand = user1Hand;
  }

  public String getuser2Hand() {
    return user2Hand;
  }

  public void setuser2Hand(String user2Hand) {
    this.user2Hand = user2Hand;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

}
