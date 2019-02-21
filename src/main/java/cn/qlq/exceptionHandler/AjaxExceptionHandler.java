package cn.qlq.exceptionHandler;

import javax.servlet.http.HttpServletRequest;

import cn.qlq.utils.JSONResultUtil;

//@RestControllerAdvice
public class AjaxExceptionHandler {
//	@ExceptionHandler(value = Exception.class)
	public JSONResultUtil defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

		e.printStackTrace();
		return JSONResultUtil.error(e.getMessage());
	}
}
