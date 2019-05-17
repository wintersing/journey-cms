package com.lt.journey_cms.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lt.journey_cms.model.Admin;

public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		// TODO Auto-generated method stub
		
		//第一步，放行公开地址
		String url = request.getRequestURI();
		
		if (url.equals("/login") || url.equals("/loginView")) {
			return true;
		}
		
		//第二步，判断是否登录
		Admin admin = (Admin) request.getSession().getAttribute("admin_session");
		
		if (admin != null) {
			return true;
		}
		
		//执行到这说明都没放行，则转发到登录页面；
		response.sendRedirect("/loginView");
//		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
					throws Exception {
		// TODO Auto-generated method stub
		
	}
}
