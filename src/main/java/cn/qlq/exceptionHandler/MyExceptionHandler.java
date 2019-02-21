package cn.qlq.exceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import cn.qlq.utils.JSONResultUtil;

@RestControllerAdvice
public class MyExceptionHandler {
	public static final String ERROR_VIEW = "error";

	@ExceptionHandler(value = Exception.class)
	public Object errorHandler(HttpServletRequest reqest, HttpServletResponse response, Exception e) throws Exception {

		e.printStackTrace();

		if (isAjax(reqest)) {
			return JSONResultUtil.error(e.getMessage());
		} else {
			ModelAndView mav = new ModelAndView();
			mav.addObject("exception", e);
			mav.addObject("url", reqest.getRequestURL());
			mav.setViewName(ERROR_VIEW);
			return mav;
		}
	}

	/**
	 * 根据请求头是否携带X-Requested-With参数判断是否是ajax请求
	 * 
	 * @param httpRequest
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest httpRequest) {
		return (httpRequest.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With").toString()));
	}
}
