package cn.qlq.exceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice
public class WebExceptionHandler {
	public static final String ERROR_VIEW = "error";

	// @ExceptionHandler(value = Exception.class)
	public Object errorHandler(HttpServletRequest reqest, HttpServletResponse response, Exception e) throws Exception {
		e.printStackTrace();
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", reqest.getRequestURL());
		mav.setViewName(ERROR_VIEW);
		return mav;
	}
}
