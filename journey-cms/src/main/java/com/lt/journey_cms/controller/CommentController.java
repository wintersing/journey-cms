package com.lt.journey_cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lt.journey_cms.model.BlogDes;
import com.lt.journey_cms.model.Comment;
import com.lt.journey_cms.model.User;
import com.lt.journey_cms.service.UserService;
import com.lt.journey_cms.service.CommentService;

@Controller
@RequestMapping("/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	private static final int pageSize = 10;

	@RequestMapping("/comment")
	public String blog(Model model, Integer page) {
		//酒店
		if (page == null) {
			page = 1;
		}
		List<Comment> commentList = commentService.findComment((page - 1)*pageSize, pageSize);
		model.addAttribute("commentList", commentList);
		int count = commentService.findCount();
		model.addAttribute("count", count);
		model.addAttribute("page", page);
		return "comment";
	}
	
	@RequestMapping(value = "/commentDel/{id}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String commentDel(Model model, @PathVariable("id") String id) {

		commentService.commentDel(id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", true);
		return jsonObject.toJSONString();
	}
	
	@RequestMapping(value = "/commentsDel/{idStr}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String commentsDel(Model model, @PathVariable("idStr") String idStr) {
		String[] ids = idStr.split(",");
		commentService.commentsDel(ids);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", true);
		return jsonObject.toJSONString();
	}
	
}
