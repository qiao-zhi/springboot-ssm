package cn.qlq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 返回freemarker相关页面
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("freemarker")
public class FreemarkerController {

	/**
	 * 会跳转到templates/freemarker/index.ftl
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/index")
	public String index(ModelMap map) {
		map.addAttribute("name", "testname");
		return "freemarker/index";
	}

	/**
	 * 会跳转到templates/freemarker/center/center.ftl
	 * 
	 * @return
	 */
	@RequestMapping("/center")
	public String center() {
		return "freemarker/center/center";
	}
}
