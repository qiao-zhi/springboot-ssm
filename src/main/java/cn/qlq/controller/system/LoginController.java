package cn.qlq.controller.system;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.qlq.bean.user.User;
import cn.qlq.utils.JSONResultUtil;

/**
 * 登陆
 * 
 * @author Administrator
 *
 */
@Controller
public class LoginController {
	/**
	 * 跳转到登陆界面
	 * 
	 * @return
	 */
	@RequestMapping("login")
	public String login() {
		return "login";
	}

	/**
	 * 处理登陆请求
	 * 
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping("doLogin")
	@ResponseBody
	public JSONResultUtil doLogin(String username, String password, HttpSession session) {
		if (!username.equals("admin") || !password.equals("admin")) {
			return JSONResultUtil.error("账号或者密码错误");
		}

		session.setAttribute("user", new User());
		return JSONResultUtil.ok();
	}
}
