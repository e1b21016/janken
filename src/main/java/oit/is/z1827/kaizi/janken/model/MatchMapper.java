package oit.is.z1827.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchMapper {

  @Select("SELECT * from matches")
  ArrayList<Match> selectAllmatches();

  @Select("SELECT * from matches where isActive =true")
  ArrayList<Match> selectisActivematch();

  @Update("Update matches set isActive=false")
  void updatebyisactive(ArrayList<Match> match);

  @Select("SELECT count(*) from matches where isActive=true")
  int selectcountmatchinfo();


  @Insert("INSERT INTO matches (user1, user2, user1Hand, user2Hand, isActive) VALUES (#{user1}, #{user2}, #{user1Hand}, #{user2Hand}, #{isActive})")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertmatch(Match match);
}
