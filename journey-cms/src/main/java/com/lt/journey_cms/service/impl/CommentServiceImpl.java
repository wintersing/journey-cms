package com.lt.journey_cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.journey_cms.dao.CommentDao;
import com.lt.journey_cms.model.Comment;
import com.lt.journey_cms.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDao commentDao;

	@Override
	public List<Comment> findComment(int i, int pagesize) {
		// TODO Auto-generated method stub
		return commentDao.findComment(i, pagesize);
	}

	@Override
	public int findCount() {
		// TODO Auto-generated method stub
		return commentDao.findCount();
	}

	@Override
	public void commentDel(String id) {
		// TODO Auto-generated method stub
		commentDao.commentDel(id);
	}

	@Override
	public void commentsDel(String[] ids) {
		// TODO Auto-generated method stub
		commentDao.commentsDel(ids);
	}


}
