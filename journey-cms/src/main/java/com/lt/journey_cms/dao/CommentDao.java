package com.lt.journey_cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.lt.journey_cms.model.Comment;

@Repository
public interface CommentDao {

	@Select("select * from comment ORDER BY publishDate DESC limit #{offset}, #{pageSize}")
	public List<Comment> findComment(@Param("offset")int offset, @Param("pageSize")int pageSize);

	@Select("select count(*) from comment")
	public int findCount();

	@Delete("delete FROM comment WHERE id = #{id}")
	public void commentDel(@Param("id")String id);

	public void commentsDel(String[] ids);

}
