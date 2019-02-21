package cn.qlq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("JSP")
@Controller
public class JSPController {
	@RequestMapping("index")
	public String index(ModelMap map) {
		map.addAttribute("name", "testname");
		return "index";
	}
}
