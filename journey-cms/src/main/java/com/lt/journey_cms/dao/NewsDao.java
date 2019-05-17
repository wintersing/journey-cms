package com.lt.journey_cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.lt.journey_cms.model.News;

@Repository
public interface NewsDao {

	public void addNews(List<News> newsList);

	@Select("select * from news ORDER BY updatetime DESC limit #{i}, #{pageSize}")
	public List<News> findNewsRecommend(@Param("i")int i, @Param("pageSize")int pageSize);

	@Select("select count(*) from news")
	public int findCount();

	@Select("select * from news where id = #{id}")
	public News findNews(@Param("id")String id);

	public void editBlog(News news);

	@Delete("delete FROM news WHERE id = #{id}")
	public void blogDel(String id);

}
