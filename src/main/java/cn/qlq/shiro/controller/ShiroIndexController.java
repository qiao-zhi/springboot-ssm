package cn.qlq.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("shiro")
public class ShiroIndexController {

	@RequestMapping("login")
	public String login() {
		return "shiro/login";
	}

	@RequestMapping("index")
	public String index() {
		return "shiro/index";
	}

	@RequestMapping("unauthorized")
	public String unauthorized() {
		return "shiro/unauthorized";
	}

}
