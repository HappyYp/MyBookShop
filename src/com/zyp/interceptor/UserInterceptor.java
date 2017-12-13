package com.zyp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
@Repository
public class UserInterceptor 
extends HandlerInterceptorAdapter {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
		
	}
	/*
	 * @Description À¹½Ø¹¦ÄÜ£¬Î´µÇÂ¼²»ÄÜ¹ºÂò
	 * @Author zyp
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		
		
		HttpSession session = request.getSession();
		Object user = session.getAttribute("user");
		System.out.println(user);
		if(user!=null) {
			
			return true;
		}else {	
		 session = request.getSession();
		 session.removeAttribute("shoppingBook");
		 response.sendRedirect(request.getContextPath()+"/error.jsp");
		 return false;
		}
	}

}
