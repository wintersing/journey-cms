package com.lt.journey_cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.journey_cms.dao.BlogDao;
import com.lt.journey_cms.model.Blog;
import com.lt.journey_cms.model.BlogDes;
import com.lt.journey_cms.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogDao blogDao;
	
	@Override
	public void addBlog(List<BlogDes> blogList) {
		// TODO Auto-generated method stub
		blogDao.addBlog(blogList);
	}

	@Override
	public List<BlogDes> findBlog(int i, int pageSize) {
		// TODO Auto-generated method stub
		return blogDao.findBlog(i, pageSize);
	}

	@Override
	public int findCount() {
		// TODO Auto-generated method stub
		return blogDao.findCount();
	}

	@Override
	public BlogDes findBlog(String id) {
		// TODO Auto-generated method stub
		return blogDao.findBlogDes(id);
	}

	@Override
	public void editBlog(BlogDes blogDes) {
		// TODO Auto-generated method stub
		blogDao.editBlog(blogDes);
	}

	@Override
	public void blogDel(String id) {
		// TODO Auto-generated method stub
		blogDao.blogDel(id);
	}

	@Override
	public void blogsDel(String[] ids) {
		// TODO Auto-generated method stub
		blogDao.blogsDel(ids);
	}

}
