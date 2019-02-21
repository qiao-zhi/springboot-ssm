package cn.qlq.controller.system;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 退出登陆
 * 
 * @author Administrator
 *
 */
@Controller
public class LogoutController {
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/login.html";
	}
}
