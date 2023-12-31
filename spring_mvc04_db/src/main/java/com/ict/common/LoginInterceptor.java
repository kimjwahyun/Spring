package com.ict.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements AsyncHandlerInterceptor{
	
	// Controller로 가기전에 구동됨
	// 결과가 true이면 Controller로 가고, 결과가 false이며 특정 JSP로 이동한다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 로그인 체크를 해서 만약 로그인이 안된 상태이면 value에 false를 저장
		HttpSession session = request.getSession(true);
		// true의 의미는
		// 만약 session이 삭제된 상태라면 새로운 session을 생성해 준다.
		// 삭제가 아뇐 상태라면 사용하고 있던 session을 그대로 전달해 준다.
		Object obj = session.getAttribute("loginChk");
		if(obj == null) {
			// 로그인 하지 않은 상태일 때
			request.getRequestDispatcher("/WEB-INF/views/login_error.jsp").forward(request, response);
			return false;
		}
		return true;
	}
	
	/*
	 Controller에서 리턴되어 뷰 리졸버로 가기전에 구동
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		AsyncHandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	 뷰 리볼버가 뷰를 찾아서 내보내고 나면 구동
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		AsyncHandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	*/
}
