package cn.qlq.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor1 implements HandlerInterceptor {

	/**
	 * 在请求处理之前进行调用（Controller方法调用之前）
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

		System.out.println("被 MyInterceptor1  postHandle拦截，放行...");
		return true;

	}

	/**
	 * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView mv)
			throws Exception {
		System.out.println("被 MyInterceptor1 postHandle 拦截，放行...");
	}

	/**
	 * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行 （主要是用于进行资源清理工作）
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex)
			throws Exception {
		System.out.println("被 MyInterceptor1 afterCompletion 拦截，放行...");
	}
}
