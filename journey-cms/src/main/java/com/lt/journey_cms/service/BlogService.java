package com.lt.journey_cms.service;

import java.util.List;

import com.lt.journey_cms.model.Blog;
import com.lt.journey_cms.model.BlogDes;
import com.lt.journey_cms.model.HotelDes;

public interface BlogService {

	public void addBlog(List<BlogDes> blogDesList);

	public List<BlogDes> findBlog(int i, int pageSize);

	public int findCount();

	public BlogDes findBlog(String id);

	public void editBlog(BlogDes blogDes);

	public void blogDel(String id);

	public void blogsDel(String[] ids);

}
