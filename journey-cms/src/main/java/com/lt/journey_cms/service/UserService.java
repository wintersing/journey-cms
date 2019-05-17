package com.lt.journey_cms.service;

import com.lt.journey_cms.model.User;

public interface UserService{
    
    public User findUserByPhone(String mobile);

	public void addUser(User user);

	public User findUserByUsername(String username);
    
}