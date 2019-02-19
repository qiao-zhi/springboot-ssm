package cn.qlq.action;

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
@RequestMapping("th")
public class ThymeleafController {

	/**
	 * 会跳转到templates/freemarker/index.html
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/index")
	public String index(ModelMap map) {
		map.addAttribute("name", "testname");
		return "thymeleaf/index";
	}

	/**
	 * 会跳转到templates/thymeleaf/center/center.html
	 * 
	 * @return
	 */
	@RequestMapping("/center")
	public String center() {
		return "thymeleaf/center/center";
	}
}
