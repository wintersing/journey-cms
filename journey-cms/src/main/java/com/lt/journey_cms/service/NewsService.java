package com.lt.journey_cms.service;

import java.util.List;

import com.lt.journey_cms.model.News;

public interface NewsService {

	public void addNews(List<News> newsList);

	public List<News> findNewsRecommend(int i, int pageSize);

	public int findCount();

	public News findNews(String id);

	public void editBlog(News news);

	public void blogDel(String id);

	public void newssDel(String[] ids);

}
