package com.lt.journey_cms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lt.journey_cms.model.Admin;
import com.lt.journey_cms.service.AdminService;

@Controller
@RequestMapping("/")
public class AdminController {

	@Autowired
	private AdminService adminService;


	@RequestMapping("/loginView")
	public String loginView(Model model) {
		return "login";
	}


	@RequestMapping(value="/login", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String login(Model model, Admin admin,HttpServletRequest req) {
		JSONObject jsonObject = null;
		Admin admin_ = adminService.findAdminByAdminname(admin.getAdminname());
		if (admin_ == null) {
			jsonObject = new JSONObject();
			jsonObject.put("status", false);
			jsonObject.put("msg", "管理员名称或密码错误！");
			return jsonObject.toJSONString();
		}else if(!admin.getPassword().equals(admin_.getPassword())) {
			jsonObject = new JSONObject();
			jsonObject.put("status", false);
			jsonObject.put("msg", "管理员名称或密码错误！");
			return jsonObject.toJSONString();
		}
		req.getSession().setAttribute("admin_session", admin_);
		jsonObject = new JSONObject();
		jsonObject.put("status", true);
		return jsonObject.toJSONString();
	}
	


	@RequestMapping(value="/logout")
	public String logout(Model model, Admin admin,HttpServletRequest req) {
		JSONObject jsonObject = null;
		req.getSession().invalidate();
		jsonObject = new JSONObject();
		jsonObject.put("status", true);
		return "redirect:/";
	}
	
	@RequestMapping("/admin")
	public String admin(Model model) {

		List<Admin> adminList = adminService.findAdmin();
		model.addAttribute("adminList", adminList);
		int count = adminService.findCount();
		model.addAttribute("count", count);
		return "admin";
	}
	

	@RequestMapping("/adminView")
	public String adminView() {
		return "adminDes";
	}	


	@RequestMapping(value="/addAdmin", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addAdmin(Model model, Admin admin, HttpServletRequest req) {//
		JSONObject jsonObject = new JSONObject();
		Admin admin_session = (Admin) req.getSession().getAttribute("admin_session");
		if (!admin_session.getRole().equals("1")) {
			jsonObject = new JSONObject();
			jsonObject.put("status", false);
			jsonObject.put("msg", "无权限！");
			return jsonObject.toJSONString();
		}
		Admin admin_ = adminService.findAdminByAdminname(admin.getAdminname());
		if (admin_ != null) {
			jsonObject = new JSONObject();
			jsonObject.put("status", false);
			jsonObject.put("msg", "该管理员名称已存在 ！");
			return jsonObject.toJSONString();
		}
		admin.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		adminService.addAdmin(admin);
		
		jsonObject.put("status", true);
		jsonObject.put("msg", "添加成功！");
		return jsonObject.toJSONString();
	}
	
	@RequestMapping(value = "/adminDel/{id}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String adminDel(Model model, @PathVariable("id") String id, HttpServletRequest req) {
		JSONObject jsonObject = new JSONObject();

		Admin admin_session = (Admin) req.getSession().getAttribute("admin_session");
		if (!admin_session.getRole().equals("1")) {
			jsonObject = new JSONObject();
			jsonObject.put("status", false);
			jsonObject.put("msg", "无权限！");
			return jsonObject.toJSONString();
		}
		adminService.adminDel(id);
		jsonObject.put("status", true);
		return jsonObject.toJSONString();
	}
	
	
}
