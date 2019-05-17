package com.lt.journey_cms.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class SMSUtils {
	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

	public static final String url ="http://v.juhe.cn/sms/send";//请求接口地址
	public static final String tpl_id ="155390";
	public static final String key ="44adfcda6f4ba3d2b831ef7ee18fd4f0";

	public static JSONObject sendSMSCode(String mobile, String tpl_value){
		String ret =null;
		Map<String, String> params = new HashMap<String, String>();//请求参数
		
		params.put("mobile",mobile);		//接受短信的用户手机号码
        params.put("tpl_id",tpl_id);		//您申请的短信模板ID，根据实际情况修改
        params.put("tpl_value",tpl_value);	//您设置的模板变量，根据实际情况修改
		params.put("key",key);				//应用APPKEY(应用详细页查询)
		try {
			
			ret = net(url, params, "POST");
			JSONObject object = JSONObject.parseObject(ret);
//			System.out.println(object);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return (JSONObject) new JSONObject().put("error_code", 500);
		}
	}


	/**
	 *
	 * @param strUrl 请求地址
	 * @param params 请求参数
	 * @param method 请求方法
	 * @return  网络请求字符串
	 * @throws Exception
	 */
	private static String net(String strUrl, Map<String, String> params,String method) throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			if(method==null || method.equalsIgnoreCase("GET")){
				strUrl = strUrl+"?"+urlencode(params);
			}
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if(method==null || method.equals("GET")){
				conn.setRequestMethod("GET");
			}else{
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}
			conn.setRequestProperty("User-agent", userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(DEF_CONN_TIMEOUT);
			conn.setReadTimeout(DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			conn.connect();
			if (params!= null && method.equalsIgnoreCase("POST")) {
				try {
					DataOutputStream out = new DataOutputStream(conn.getOutputStream());
					out.writeBytes(urlencode(params));
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}
			rs = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return rs;
	}
	
	
	//将map型转为请求参数型
	private static String urlencode(Map<String,String> data) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry i : data.entrySet()) {
			try {
				sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}