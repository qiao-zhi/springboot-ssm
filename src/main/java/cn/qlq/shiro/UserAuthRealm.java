package cn.qlq.shiro;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import cn.qlq.shiro.bean.Permission;
import cn.qlq.shiro.bean.Role;
import cn.qlq.shiro.bean.ShiroUser;
import cn.qlq.shiro.mapper.ShiroUserMapper;

@Component
public class UserAuthRealm extends AuthorizingRealm {

	@Autowired
	private ShiroUserMapper shiroUserMapper;

	/**
	 * 权限核心配置 根据数据库中的该用户 角色 和 权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		ShiroUser user = (ShiroUser) principals.getPrimaryPrincipal();
		for (Role role : user.getRoles()) { // 获取 角色
			authorizationInfo.addRole(role.getName()); // 添加 角色
			for (Permission permission : role.getPermissions()) { // 获取 权限
				authorizationInfo.addStringPermission(permission.getName());// 添加
																			// 权限
			}
		}

		return authorizationInfo;
	}

	/**
	 * 用户登陆凭证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		ShiroUser user = shiroUserMapper.findByUsername(username);
		if (user == null) {
			return null;
		}

		AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		return authenticationInfo;
	}

	// 清除缓存
	public void clearCache() {
		PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principalCollection);
	}
}