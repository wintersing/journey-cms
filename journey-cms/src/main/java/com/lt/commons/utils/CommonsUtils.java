package com.lt.commons.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * 当把Person类作为BeanUtilTest的内部类时，程序出错<br>
 * java.lang.NoSuchMethodException: Property '**' has no setter method<br>
 * 本质：内部类 和 单独文件中的类的区别 <br>
 * BeanUtils.populate方法的限制：<br>
 * The class must be public, and provide a public constructor that accepts no
 * arguments. <br>
 * This allows tools and applications to dynamically create new instances of
 * your bean, <br>
 * without necessarily knowing what Java class name will be used ahead of time
 */
public class CommonsUtils {
	
	/**
	 * MM/dd/yyyy格式转yyyy-MM-dd格式
	 * @param format
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String format(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");  
		Date date_=sdf.parse(date); 
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	    return dateFormat.format(date_);
	}

	// Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	public static Map<String, Object> beantoMap(Object obj) {

		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value);
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}

		return map;

	}

	/**
	 *获取 properties中一个属性
	 * 使用java.util.Properties类的load()方法加载properties文件
	 */
	public static String getProperties(String path, String attr) {
		try {
			// 获取文件流（方法1或2均可）
			InputStream inputStream = new BufferedInputStream(new FileInputStream(new File(path))); // 方法1
			// InputStream inputStream =
			// Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
			// //方法2
			Properties prop = new Properties();

			prop.load(new InputStreamReader(inputStream, "UTF-8")); // 加载格式化后的流

			return prop.getProperty(attr);

		} catch (FileNotFoundException e) {
			System.out.println("properties文件路径有误！");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 *获取 properties中所有属性
	 * 使用java.util.Properties类的load()方法加载properties文件
	 */
	public static Properties getPropertiesAll(String path) {
		try {
			// 获取文件流（方法1或2均可）
			InputStream inputStream = new BufferedInputStream(new FileInputStream(new File(path))); // 方法1
			// InputStream inputStream =
			// Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
			// //方法2
			Properties prop = new Properties();

			prop.load(new InputStreamReader(inputStream, "UTF-8")); // 加载格式化后的流

			return prop;

		} catch (FileNotFoundException e) {
			System.out.println("properties文件路径有误！");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T xmlToBean(String XML, Class<T> load) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(load);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (T) unmarshaller.unmarshal(new StringReader(XML));
	}

	/**
	 * 获取字符串的unicode编码 汉字“木”的Unicode 码点为Ox6728
	 *
	 * @param s 木
	 * @return \ufeff\u6728 \ufeff控制字符 用来表示「字节次序标记（Byte Order Mark）」不占用宽度
	 *         在java中一个char是采用unicode存储的 占用2个字节 比如 汉字木 就是 Ox6728
	 *         4bit+4bit+4bit+4bit=2字节
	 */
	public static String stringToUnicode(String s) {
		try {
			StringBuffer out = new StringBuffer("");
			// 直接获取字符串的unicode二进制
			byte[] bytes = s.getBytes("unicode");
			// 然后将其byte转换成对应的16进制表示即可
			for (int i = 0; i < bytes.length - 1; i += 2) {
				out.append("\\u");
				String str = Integer.toHexString(bytes[i + 1] & 0xff);
				for (int j = str.length(); j < 2; j++) {
					out.append("0");
				}
				String str1 = Integer.toHexString(bytes[i] & 0xff);
				out.append(str1);
				out.append(str);
			}
			return out.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Unicode转 汉字字符串
	 *
	 * @param str \u6728
	 * @return '木' 26408
	 */
	public static String unicodeToString(String str) {

		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find()) {
			// group 6728
			String group = matcher.group(2);
			// ch:'木' 26408
			ch = (char) Integer.parseInt(group, 16);
			// group1 \u6728
			String group1 = matcher.group(1);
			str = str.replace(group1, ch + "");
		}
		return str;
	}

	/**
	 * 获取URL图片的文件大小
	 * @param imgUrl
	 * @return
	 * @throws IOException
	 */
	public static int getImgUrlSize(String imgUrl) {
		int size = 0;
		URL url;
		try {
			url = new URL(imgUrl);
			HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
			// 根据响应获取文件大小
			size = urlcon.getContentLength();
			urlcon.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return size;
	}
}