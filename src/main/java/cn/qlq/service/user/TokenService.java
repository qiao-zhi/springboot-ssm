package cn.qlq.service.user;

import cn.qlq.bean.user.Token;

public interface TokenService {

	Token addOrUpdateToken(String username);

	/**
	 * 根据username查询token
	 * 
	 * @param username
	 * @return
	 */
	Token findTokenByUsername(String username);

	/**
	 * 根据串查询Token,如果存在根据失效日期进行更新；不存在返回null
	 * 
	 * @param tokenStr
	 * @return
	 */
	Token updateIfExistsTokenByTokenStr(String tokenStr);

	/**
	 * 删除无效的token
	 */
	void deleteInvalidToken();
}
