package com.lt.journey_cms.service;

import java.util.List;

import com.lt.journey_cms.model.Comment;

public interface CommentService {

	public List<Comment> findComment(int i, int pagesize);

	public int findCount();

	public void commentDel(String id);

	public void commentsDel(String[] ids);

}
