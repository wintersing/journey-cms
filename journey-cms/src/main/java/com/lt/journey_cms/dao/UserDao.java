package com.lt.journey_cms.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.lt.journey_cms.model.User;

@Repository
public interface UserDao {
    
    @Select("SELECT * FROM user WHERE username=#{username}")
    public User findUserByUsername(@Param("username")String username);
    
    @Select("SELECT * FROM user WHERE phone=#{mobile}")
    public User findUserByPhone(@Param("mobile")String mobile);
    
	public void addUser(User user);
	
}