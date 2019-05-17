package com.lt.journey_cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.lt.journey_cms.model.User;

@Repository
public interface UserDao {

	@Select("select * from user ORDER BY createTime DESC limit #{offset}, #{pageSize}")
	public List<User> findUser(@Param("offset")int offset, @Param("pageSize")int pageSize);

	@Select("select count(*) from user")
	public int findCount();
	
}