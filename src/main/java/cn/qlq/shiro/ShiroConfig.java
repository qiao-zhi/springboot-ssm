package cn.qlq.shiro;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import cn.qlq.shiro.bean.Permission;
import cn.qlq.shiro.mapper.PermissionMapper;

//@Configuration
public class ShiroConfig {

	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private UserAuthRealm userAuthRealm;

	/**
	 * 配置 资源访问策略 . web应用程序 shiro核心过滤器配置
	 */
	@Bean
	public ShiroFilterFactoryBean factoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
		factoryBean.setSecurityManager(securityManager);
		factoryBean.setLoginUrl("/shiro/login.html");// 登录页
		// 首页(这个不需要设置，因为是JS登录之后自己重定向)
		// factoryBean.setSuccessUrl("/shiro/index.html");
		factoryBean.setUnauthorizedUrl("/shiro/unauthorized.html");// 未授权界面;

		// 自定义filter配置（ 配置 拦截过滤器链）
		factoryBean.setFilterChainDefinitionMap(setFilterChainDefinitionMap());
		return factoryBean;
	}

	/**
	 * 配置 SecurityManager,可配置一个或多个realm
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(userAuthRealm);
		// securityManager.setRealm(xxxxRealm);
		return securityManager;
	}

	/**
	 * 开启shiro 注解支持. 使以下注解能够生效 : 需要认证
	 * {@link org.apache.shiro.authz.annotation.RequiresAuthentication
	 * RequiresAuthentication} 需要用户
	 * {@link org.apache.shiro.authz.annotation.RequiresUser RequiresUser} 需要访客
	 * {@link org.apache.shiro.authz.annotation.RequiresGuest RequiresGuest}
	 * 需要角色 {@link org.apache.shiro.authz.annotation.RequiresRoles
	 * RequiresRoles} 需要权限
	 * {@link org.apache.shiro.authz.annotation.RequiresPermissions
	 * RequiresPermissions}
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * 配置 拦截过滤器链. map的键 : 资源地址 ; map的值 : 所有默认Shiro过滤器实例名 默认Shiro过滤器实例 参考 :
	 * {@link org.apache.shiro.web.filter.mgt.DefaultFilter}
	 */
	private Map<String, String> setFilterChainDefinitionMap() {
		Map<String, String> filterMap = new LinkedHashMap<>();
		// 注册 数据库中所有的权限 及其对应url
		List<Permission> allPermission = permissionMapper.findAll();// 数据库中查询所有权限
		for (Permission p : allPermission) {
			filterMap.put(p.getUrl(), "perms[" + p.getName() + "]"); // 拦截器中注册所有的权限
		}
		filterMap.put("/static/**", "anon"); // 公开访问的资源
		filterMap.put("/shiro/doLogin.html", "anon"); // 登录地址放开
		filterMap.put("/logout", "logout"); // 配置登出页,shiro已经帮我们实现了跳转
		filterMap.put("/**", "authc"); // 所有资源都需要经过验证
		return filterMap;
	}

	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}
}