package com.lt.journey_cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.lt.journey_cms.model.Admin;

@Repository
public interface AdminDao {

	@Select("select * from admin order by createtime Desc")
	public List<Admin> findAdmin();

	@Select("select count(*) from admin")
	public int findCount();

	public void addAdmin(Admin admin);

	@Delete("delete FROM admin WHERE id = #{id}")
	public void adminDel(String id);

	@Select("select * from admin where adminname = #{adminname}")
	public Admin findAdminByAdminname(@Param("adminname")String adminname);

}
