package com.lt.journey_cms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lt.journey_cms.model.BlogParam;
import com.lt.journey_cms.util.BlogInfo;
import com.lt.journey_cms.model.BlogDes;
import com.lt.journey_cms.model.HotelDes;
import com.lt.journey_cms.model.PlacesDes;
import com.lt.journey_cms.model.PlacesParam;
import com.lt.journey_cms.service.BlogService;
import com.lt.journey_cms.service.HotelService;
import com.lt.journey_cms.service.PlacesService;
import com.lt.journey_cms.util.PlacesInfo;

@Controller
@RequestMapping("/")
public class BlogController {
	
	@Autowired
	private PlacesService placesService;
	
	@Autowired
	private BlogService blogService;
	
	private static final int pageSize = 10;

	private static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	/**
	 * 游记列表
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping("/blog")
	public String blog(Model model, Integer page) {
		//酒店
		if (page == null) {
			page = 1;
		}
		List<BlogDes> blogList = blogService.findBlog((page - 1)*pageSize, pageSize);
		model.addAttribute("blogList", blogList);
		int count = blogService.findCount();
		model.addAttribute("count", count);
		model.addAttribute("page", page);
		return "blog";
	}
	

	/**
	 * 游记详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/blog/{id}")
	public String blogDes(Model model, @PathVariable("id") String id) {
		BlogDes blogDes = blogService.findBlog(id);
		model.addAttribute("blogItem", blogDes);
		return "blogDes";
	}
	
	
	/**
	 *游记编辑 
	 * @param model
	 * @param places
	 * @return
	 */
	@RequestMapping("/blogEdit")
	public String blogEdit(Model model, BlogDes blogDes, HttpServletRequest req) {

		blogDes.setUpdatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		blogService.editBlog(blogDes);
		model.addAttribute("reqURI", req.getRequestURI());

		return "forward:/blog/" + blogDes.getId();
	}
	

	/**
	 * 游记删除
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/blogDel/{id}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String blogDel(Model model, @PathVariable("id") String id) {

		blogService.blogDel(id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", true);
		return jsonObject.toJSONString();
	}
	

	/**
	 * 调用接口添加景点
	 * @param model
	 * @param cityName
	 * @param pageToken
	 * @return
	 */
	@RequestMapping(value = "/blogapi", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String blogapi(Model model, String cityName, String pageToken) {
		JSONObject jsonObject = new JSONObject();

		try {
			cityName = new String(cityName.getBytes("ISO-8859-1"), "utf-8");

			String cityid = placesService.findCityidByCityName(cityName);
			if (cityid == null) {
				jsonObject.put("status", false);
				jsonObject.put("msg", "请输入正确的城市！");
				return jsonObject.toJSONString();
			}
			if (!isNumber(pageToken)) {
				jsonObject.put("status", false);
				jsonObject.put("msg", "请输入正确的页数！");
				return jsonObject.toJSONString();
			}
			BlogParam blogParam = new BlogParam();
			blogParam.setCityid(cityid);//1,1005,1096,360,26
			blogParam.setSort("0");
			blogParam.setPageToken(pageToken);
			List<BlogDes> blogDesList = BlogInfo.getBlogInfo(blogParam, BlogDes.class);
			blogService.addBlog(blogDesList);
			
			jsonObject.put("status", true);
			jsonObject.put("msg", "添加成功！");
			return jsonObject.toJSONString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonObject.put("status", false);
			jsonObject.put("msg", "添加失败，请稍后重试！");
			return jsonObject.toJSONString();
		}
	}
	
	
	@RequestMapping(value = "/blogsDel/{idStr}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String blogsDel(Model model, @PathVariable("idStr") String idStr) {
		String[] ids = idStr.split(",");
		blogService.blogsDel(ids);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", true);
		return jsonObject.toJSONString();
	}

}
