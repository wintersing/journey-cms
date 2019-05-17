package com.lt.journey_cms.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lt.commons.utils.CommonsUtils;
import com.lt.commons.utils.HttpRequest;
import com.lt.journey_cms.exception.MessageException;
import com.lt.journey_cms.model.Blog;
import com.lt.journey_cms.model.BlogDes;
import com.lt.journey_cms.model.BlogParam;

public class BlogInfo {

	private static Properties propertiesAll = CommonsUtils.getPropertiesAll("src/main/resources/apikey.properties");
//	private static Properties propertiesAll = CommonsUtils.getPropertiesAll("apikey.properties");
	
	private static final String url = propertiesAll.getProperty("Blog_Url");
	private static final String apikey = propertiesAll.getProperty("IDataAPI_APIKEY");

	public static <T> List<T> getBlogInfo(BlogParam blogParam, Class<T> clazz) throws MessageException {
		//拼接参数
		StringBuffer param = new StringBuffer();
		param.append("apikey=").append(apikey).append("&cityid=").append(blogParam.getCityid())
		.append("&sort=").append(blogParam.getSort()).append("&pageToken=").append(blogParam.getPageToken());
		
		//发送Get请求
		String ret = HttpRequest.sendGet(url, param.toString());
//		String dataStr = CommonsUtils.unicodeToString(ret);
//		System.out.println(dataStr);
		if (ret == null) {
			throw new MessageException("Search No Result");
		}

		JSONObject dataObj = JSON.parseObject(ret);
		if (dataObj.getString("retcode").equals("100002")) {
			throw new MessageException("Search No Result");
		}
		String blogStr = dataObj.getJSONArray("data").toString();
		
		List<T> blogList = JSONObject.parseArray(blogStr, clazz);

		String cityid = blogParam.getCityid();
		for (T blog : blogList) {
			if (blog instanceof Blog) {
				Blog blogtemp = (Blog) blog;
				blogtemp.setCityid(cityid);
				blogtemp.setCity(CommonsUtils.unicodeToString(blogtemp.getCity()));
				blogtemp.setPosterScreenName(CommonsUtils.unicodeToString(blogtemp.getPosterScreenName()));
			} else if(blog instanceof BlogDes) {
				BlogDes blogtemp = (BlogDes) blog;
//				System.out.println(blogtemp == blog);
				blogtemp.setCityid(cityid);
//				blogtemp.setCity(CommonsUtils.unicodeToString(blogtemp.getCity()));
				blogtemp.setPosterScreenName(CommonsUtils.unicodeToString(blogtemp.getPosterScreenName()));
				blogtemp.setUpdatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			}
		}
		
		return blogList;
	}
	

	public static BlogDes getBlogDesInfo(BlogParam blogParam) {
		StringBuffer param = new StringBuffer();
		param.append("apikey=").append(apikey).append("&cityid=").append(blogParam.getCityid())
		.append("&sort=").append(blogParam.getSort()).append("&pageToken=").append(blogParam.getPageToken());
		
		String ret = HttpRequest.sendGet(url, param.toString());
		String dataStr = CommonsUtils.unicodeToString(ret);
		System.out.println(dataStr);
		JSONObject dataObj = JSON.parseObject(ret);
		JSONArray blogList = dataObj.getJSONArray("data");

		//循环取出目标游记
		BlogDes blogDes = null;
		for (Object obj_ : blogList) {
			if (obj_ instanceof JSONObject) {
				JSONObject obj = (JSONObject) obj_;
				String id = obj.getString("id");
				if (blogParam.getId().equals(id)) {
					blogDes = JSONObject.parseObject(obj+"", BlogDes.class);
					break;
				}
			}
		}

		return blogDes;
	}

	@Test
	public void name() {
		BlogParam blogParam = new BlogParam();
		blogParam.setCityid("26");
//		List<? extends Object> blogInfo = getBlogInfo(blogParam);
//		System.out.println(blogInfo);
	}

}










