package oit.is.z1827.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("SELECT * from users where id = #{id}")
  User selectById(int id);

  @Select("SELECT * from users")
  ArrayList<User> selectAllusers();

  @Select("SELECT * from users where userName = #{userName}")
  ArrayList<User> selectAllByuserName(String userName);

}
