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

import com.lt.journey_cms.service.HotelService;
import com.lt.journey_cms.util.PlacesInfo;
import com.alibaba.fastjson.JSONObject;
import com.lt.journey_cms.model.HotelParam;
import com.lt.journey_cms.util.HotelInfo;
import com.lt.journey_cms.model.Hotel;
import com.lt.journey_cms.model.HotelDes;
import com.lt.journey_cms.model.PlacesDes;
import com.lt.journey_cms.model.PlacesParam;

@Controller
@RequestMapping("/")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	private static final int pageSize = 10;

	private static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 酒店列表
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping("/hotel")
	public String hotel(Model model, Integer page) {
		//酒店
		if (page == null) {
			page = 1;
		}
		List<HotelDes> hotelList = hotelService.findHotelRecommend((page - 1)*pageSize, pageSize);
		model.addAttribute("hotelList", hotelList);
		int count = hotelService.findCount();
		model.addAttribute("count", count);
		model.addAttribute("page", page);
		return "hotel";
	}
	

	/**
	 * 景点详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/hotel/{id}")
	public String placesDes(Model model, @PathVariable("id") String id) {
		HotelDes hotelDes = hotelService.findHotel(id);
		model.addAttribute("hotelItem", hotelDes);
		return "hotelDes";
	}
	
	
	/**
	 *景点编辑 
	 * @param model
	 * @param places
	 * @return
	 */
	@RequestMapping("/hotelEdit")
	public String placesEdit(Model model, HotelDes hotelDes, HttpServletRequest req) {

		hotelDes.setUpdatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		hotelService.editHotel(hotelDes);
		model.addAttribute("reqURI", req.getRequestURI());

		return "forward:/hotel/" + hotelDes.getId();
	}
	
	/**
	 * 调用接口添加景点
	 * @param model
	 * @param cityName
	 * @param pageToken
	 * @return
	 */
	@RequestMapping(value = "/hotelapi", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String placesapi(Model model, String city, String level, String pageToken) {
		JSONObject jsonObject = new JSONObject();

		try {
			city = new String(city.getBytes("ISO-8859-1"), "utf-8");
			level = new String(level.getBytes("ISO-8859-1"), "utf-8");

			if (!isNumber(pageToken)) {
				jsonObject.put("status", false);
				jsonObject.put("msg", "请输入正确的页数！");
				return jsonObject.toJSONString();
			}
			HotelParam hotelParam = new HotelParam();
			hotelParam.setCity(city);
			hotelParam.setLevel(level);
			List<HotelDes> hotelList = HotelInfo.getHotelInfo(hotelParam, HotelDes.class);
			hotelService.addHotel(hotelList);
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
	

	/**
	 * 游记删除
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/hotelDel/{id}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String hotelDel(Model model, @PathVariable("id") String id) {

		hotelService.hotelDel(id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", true);
		return jsonObject.toJSONString();
	}
	
}
