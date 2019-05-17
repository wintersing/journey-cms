package com.lt.journey_cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lt.journey_cms.model.User;
import com.lt.journey_cms.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private static final int pageSize = 10;

	@RequestMapping("/user")
	public String blog(Model model, Integer page) {
		//酒店
		if (page == null) {
			page = 1;
		}
		List<User> userList = userService.findUser((page - 1)*pageSize, pageSize);
		model.addAttribute("userList", userList);
		int count = userService.findCount();
		model.addAttribute("count", count);
		model.addAttribute("page", page);
		return "user";
	}
}
