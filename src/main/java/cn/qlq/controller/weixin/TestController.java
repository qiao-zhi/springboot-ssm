package cn.qlq.controller.weixin;

import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/weixin/test")
public class TestController {

	/**
	 * 首页，跳转到index.html,index.html有一个连接会访问下面的login方法
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("connection", "close");
		System.out.println(request);
		return "test/index";
	}

	/**
	 * 接收表单数据(这里用request直接接收)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/test")
	@ResponseBody
	public String test(HttpServletRequest request, HttpServletResponse response) {
		String method = request.getMethod();
		System.out.println("========================");
		System.out.println("request.getMethod(): " + method);

		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = (String) parameterNames.nextElement();
			String parameterValue = request.getParameter(parameterName);
			System.out.println("  parameterName: " + parameterName + " , parameterValue: " + parameterValue + " ");
		}

		return "success";
	}

}
