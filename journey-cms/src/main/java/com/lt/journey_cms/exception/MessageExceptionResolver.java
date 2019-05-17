package com.lt.journey_cms.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MessageExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
			Object handler,	Exception e) {
		// TODO Auto-generated method stub
		ModelAndView mav = new ModelAndView();
		
		if (e instanceof MessageException) {
			mav.addObject("msg", e.getMessage());
			mav.setViewName("msg");
		}
		
		return mav;
	}

}
