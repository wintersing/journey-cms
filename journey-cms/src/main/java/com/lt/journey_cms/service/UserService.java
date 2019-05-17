package com.lt.journey_cms.service;

import java.util.List;

import com.lt.journey_cms.model.User;

public interface UserService{
    
	public List<User> findUser(int i, int pagesize);

	public int findCount();
    
}