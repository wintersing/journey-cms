package com.lt.journey_cms.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.lt.journey_cms.model.NewsParam;
import com.lt.journey_cms.util.NewsInfo;
import com.lt.journey_cms.model.BlogDes;
import com.lt.journey_cms.model.BlogParam;
import com.lt.journey_cms.model.News;
import com.lt.journey_cms.service.NewsService;
import com.lt.journey_cms.util.BlogInfo;

@Controller
@RequestMapping("/")
public class NewsController {
	
	@Autowired
	private NewsService newsService;

	private static final int pageSize = 10;

	private static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	/**
	 * 资讯列表
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping("/news")
	public String blog(Model model, Integer page) {
		//酒店
		if (page == null) {
			page = 1;
		}
		List<News> newsList = newsService.findNewsRecommend((page - 1)*pageSize, pageSize);
		model.addAttribute("newsList", newsList);
		int count = newsService.findCount();
		model.addAttribute("count", count);
		model.addAttribute("page", page);
		return "news";
	}


	/**
	 * 资讯详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/news/{id}")
	public String blogDes(Model model, @PathVariable("id") String id) {
		News news = newsService.findNews(id);
		model.addAttribute("newsItem", news);
		return "newsDes";
	}
	

	
	/**
	 *咨询编辑 
	 * @param model
	 * @param places
	 * @return
	 */
	@RequestMapping("/newsEdit")
	public String newsEdit(Model model, News news, HttpServletRequest req) {

		news.setUpdatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		newsService.editBlog(news);
		model.addAttribute("reqURI", req.getRequestURI());
		
		return "forward:/news/" + news.getId();
	}

	/**
	 * 咨询删除
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/newsDel/{id}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String blogDel(Model model, @PathVariable("id") String id) {

		newsService.blogDel(id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", true);
		return jsonObject.toJSONString();
	}


	/**
	 * 调用接口添加资讯
	 * @param model
	 * @param cityName
	 * @param pageToken
	 * @return
	 */
	@RequestMapping(value = "/newsapi", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String newsapi(Model model, String size, String catLabel2, String pageToken_news) {
		JSONObject jsonObject = new JSONObject();

		try {
			catLabel2 = new String(catLabel2.getBytes("ISO-8859-1"), "utf-8");

			if (!isNumber(size)) {
				jsonObject.put("status", false);
				jsonObject.put("msg", "请输入正确的参数！");
				return jsonObject.toJSONString();
			}
			//处理时间
			Calendar calendar = Calendar.getInstance(); 
			calendar.setTime(new Date());
			long endTime = calendar.getTimeInMillis();
			calendar.add(Calendar.DATE, -15);
			long startTime = calendar.getTimeInMillis();
			
			NewsParam newsParam = new NewsParam();
			newsParam.setCatLabel2(catLabel2);
			String time = ""+startTime/1000L+","+endTime/1000L;
			newsParam.setCreateDateRange(time);
			newsParam.setPublishDateRange(time);
			newsParam.setSize(size);
			newsParam.setPageToken_news(pageToken_news);
			List<News> newsList = NewsInfo.getNewsInfo(newsParam);
			
			newsService.addNews(newsList);
			
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
	
	
	@RequestMapping(value = "/newssDel/{idStr}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String newssDel(Model model, @PathVariable("idStr") String idStr) {
		String[] ids = idStr.split(",");
		newsService.newssDel(ids);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", true);
		return jsonObject.toJSONString();
	}

}
