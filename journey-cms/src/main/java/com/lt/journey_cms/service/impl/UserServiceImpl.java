package com.lt.journey_cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.journey_cms.dao.UserDao;
import com.lt.journey_cms.model.User;
import com.lt.journey_cms.service.UserService;;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;

	@Override
	public List<User> findUser(int i, int pagesize) {
		// TODO Auto-generated method stub
		return userDao.findUser(i, pagesize);
	}

	@Override
	public int findCount() {
		// TODO Auto-generated method stub
		return userDao.findCount();
	}


	
}