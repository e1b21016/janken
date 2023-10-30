package oit.is.z1827.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchinfoMapper {

  @Select("SELECT * from matchinfo where isActive=true")
  ArrayList<Matchinfo> selectAllmatchinfo();

  @Select("SELECT count(*) from matchinfo where isActive=true and user1 =#{user}")
  int selectuser1matchinfo(int user);

  @Select("SELECT count(*) from matchinfo where isActive=true and user2 =#{user}")
  int selectuser2matchinfo(int user);

  @Select("SELECT * from matchinfo where isActive=true and user2=#{user}")
  Matchinfo selectusermatchinfo(int user);

  @Insert("INSERT INTO Matchinfo (user1, user2, user1Hand, isActive) VALUES (#{user1}, #{user2}, #{user1Hand}, #{isActive})")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertmatchinfo(Matchinfo matchinfo);

  @Update("Update matchinfo set isActive=false")
  void updatebyisactive(Matchinfo matchinfo);
}
