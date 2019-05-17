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

import com.lt.journey_cms.service.PlacesService;
import com.alibaba.fastjson.JSONObject;
import com.lt.journey_cms.model.PlacesParam;
import com.lt.journey_cms.util.PlacesInfo;
import com.lt.journey_cms.model.GeoPoint;
import com.lt.journey_cms.model.Places;
import com.lt.journey_cms.model.PlacesDes;

@Controller
@RequestMapping("/")
public class PlacesController {
	@Autowired
	private PlacesService placesService;

	private static final int pageSize = 10;

	private static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 获取景点列表
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping("/places")
	public String places(Model model, Integer page) {
		// 景点
		if (page == null) {
			page = 1;
		}
		List<Places> placesList = placesService.findPlacesRecommend((page - 1) * pageSize, pageSize);
		model.addAttribute(placesList);

		int count = placesService.findCount();
		model.addAttribute("count", count);
		model.addAttribute("page", page);
		return "places";
	}

	/**
	 * 景点详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/places/{id}")
	public String placesDes(Model model, @PathVariable("id") String id) {
		PlacesDes places = placesService.findPlaces(id);
		model.addAttribute("placesItem", places);
		return "placesDes";
	}
	
	/**
	 *景点编辑 
	 * @param model
	 * @param places
	 * @return
	 */
	@RequestMapping("/placesEdit")
	public String placesEdit(Model model, PlacesDes places, HttpServletRequest req) {

		places.setUpdatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		placesService.editPlaces(places);
		model.addAttribute("reqURI", req.getRequestURI());

		return "forward:/places/" + places.getId();
	}

	/**
	 * 景点删除
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/placesDel/{id}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String placesDel(Model model, @PathVariable("id") String id) {

		placesService.placesDel(id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", true);
		return jsonObject.toJSONString();
	}

	/**
	 * 景点添加页面
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/addPlaces")
	public String addPlaces(Model model, HttpServletRequest req) {
		model.addAttribute("reqURI", req.getRequestURI());
		return "placesDes";
	}

	/**
	 * 景点添加
	 * @param model
	 * @param places
	 * @return
	 */
	@RequestMapping("/placesAdd")
	public String placesAdd(Model model, PlacesDes places, HttpServletRequest req) {

		places.setUpdatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		places.setGeoPoint(new GeoPoint());
		placesService.placesAdd(places);
		model.addAttribute("reqURI", req.getRequestURI());
		return "forward:/places/" + places.getId();
	}

	/**
	 * 调用接口添加景点
	 * @param model
	 * @param cityName
	 * @param pageToken
	 * @return
	 */
	@RequestMapping(value = "/placesapi", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String placesapi(Model model, String cityName, String pageToken) {
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
			PlacesParam placesParam = new PlacesParam();
			placesParam.setCityid(cityid);
			placesParam.setSort("1");
			placesParam.setPageToken(pageToken);
			List<PlacesDes> placesList = PlacesInfo.getPlacesInfo(placesParam, PlacesDes.class);
			placesService.addPlaces(placesList);
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

}
