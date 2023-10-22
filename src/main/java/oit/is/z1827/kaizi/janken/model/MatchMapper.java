package oit.is.z1827.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchMapper {

  @Select("SELECT * from matches")
  ArrayList<Match> selectAllmatches();

  @Select("SELECT * from matches where userName = #{userName}")
  ArrayList<Match> selectAllBymatcheshand(String matcheshand);

}
