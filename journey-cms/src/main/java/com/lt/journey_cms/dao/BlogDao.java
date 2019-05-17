package com.lt.journey_cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.lt.journey_cms.model.Blog;
import com.lt.journey_cms.model.BlogDes;

@Repository
public interface BlogDao {

	public void addBlog(List<BlogDes> blogList);
	
	@Select("select * from blog ORDER BY updatetime DESC limit #{offset}, #{pageSize}")
	public List<BlogDes> findBlog(@Param("offset")int offset, @Param("pageSize")int pageSize);

	@Select("select count(*) from blog")
	public int findCount();
	
	@Select("select * from blog where id = #{id}")
	public BlogDes findBlogDes(@Param("id")String id);

	public void editBlog(BlogDes blogDes);

	@Delete("delete FROM blog WHERE id = #{id}")
	public void blogDel(@Param("id")String id);

	public void blogsDel(String[] ids);

}
