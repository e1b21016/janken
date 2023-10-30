package oit.is.z1827.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchinfoMapper {

  @Select("SELECT * from matchinfo where isActive=true")
  ArrayList<Matchinfo> selectAllmatchinfo();

  @Insert("INSERT INTO Matchinfo (user1, user2, user1Hand, isActive) VALUES (#{user1}, #{user2}, #{user1Hand}, #{isActive})")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertmatchinfo(Matchinfo matchinfo);

}