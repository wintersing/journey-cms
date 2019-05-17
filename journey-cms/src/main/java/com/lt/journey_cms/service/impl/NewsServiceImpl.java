package com.lt.journey_cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.journey_cms.dao.NewsDao;
import com.lt.journey_cms.model.News;
import com.lt.journey_cms.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	private NewsDao newsDao;

	@Override
	public void addNews(List<News> newsList) {
		// TODO Auto-generated method stub
		newsDao.addNews(newsList);
	}

	@Override
	public List<News> findNewsRecommend(int i, int pageSize) {
		// TODO Auto-generated method stub
		return newsDao.findNewsRecommend(i, pageSize);
	}

	@Override
	public int findCount() {
		// TODO Auto-generated method stub
		return newsDao.findCount();
	}

	@Override
	public News findNews(String id) {
		// TODO Auto-generated method stub
		return newsDao.findNews(id);
	}

	@Override
	public void editBlog(News news) {
		// TODO Auto-generated method stub
		newsDao.editBlog(news);
	}

	@Override
	public void blogDel(String id) {
		// TODO Auto-generated method stub
		newsDao.blogDel(id);
	}

	@Override
	public void newssDel(String[] ids) {
		// TODO Auto-generated method stub
		newsDao.newssDel(ids);
	}

}
