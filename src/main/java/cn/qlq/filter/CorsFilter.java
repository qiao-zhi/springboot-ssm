package cn.qlq.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * 允许跨域请求
 */
// @WebFilter(filterName = "corsFilter", urlPatterns = "/*")
public class CorsFilter implements Filter {
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// 允许访问的源
		String headerOrigin = request.getHeader("Origin");
		if (StringUtils.isNotBlank(headerOrigin)) {
			response.setHeader("Access-Control-Allow-Origin", headerOrigin);
		}

		// 允许访问的方法
		response.setHeader("Access-Control-Allow-Methods", "*");

		// 正确的响应预检请求
		// response.setHeader("Access-Control-Allow-Headers", "Content-Type");

		// 允许自定义的请求头(根据自定义请求头)
		String headers = request.getHeader("Access-Control-Request-Headers");
		if (StringUtils.isNotBlank(headers)) {
			response.addHeader("Access-Control-Allow-Headers", headers);
		}

		// 允许预检命令缓存的时间
		response.setHeader("Access-Control-Max-Age", "3600");

		// 允许cookie
		response.setHeader("Access-Control-Allow-Credentials", "true");

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}