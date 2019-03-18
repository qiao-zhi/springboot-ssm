package cn.qlq.service.impl.user;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.qlq.bean.user.Token;
import cn.qlq.bean.user.TokenExample;
import cn.qlq.bean.user.TokenExample.Criteria;
import cn.qlq.mapper.user.TokenMapper;
import cn.qlq.service.user.TokenService;
import cn.qlq.utils.DefaultValue;
import cn.qlq.utils.MD5Util;

@Service
@Transactional
public class TokenServiceImpl implements TokenService {
	@Autowired
	private TokenMapper tokenMapper;

	@Override
	public Token addOrUpdateToken(String username) {
		// 1.判断是否有对应的token,如果有的话更新时间，没有的话就创建token并且返回
		Token token = findTokenByUsername(username);
		// 1.1创建token并返回
		if (token == null) {
			return generateAndSaveTokenByUserName(username);
		}

		// 1.2根据失效时间更新且返回token
		return updateTokenByTokenLoseTime(token);
	}

	@Override
	public Token findTokenByUsername(String username) {
		TokenExample example = new TokenExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andUsernameEqualTo(username);
		List<Token> tokens = tokenMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(tokens)) {
			return null;
		}
		return tokens.get(0);
	}

	@Override
	public Token updateIfExistsTokenByTokenStr(String tokenStr) {
		TokenExample example = new TokenExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andTokenstrEqualTo(tokenStr);
		List<Token> tokens = tokenMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(tokens)) {
			return null;
		}
		Token token = tokens.get(0);
		Token tokenUpdated = updateTokenByTokenLoseTime(token);
		return tokenUpdated;
	}

	/**
	 * 生成且保存Token
	 * 
	 * @param username
	 * @return
	 */
	private Token generateAndSaveTokenByUserName(String username) {
		Token token;
		token = new Token();
		token.setUsername(username);
		Date now = new Date();
		token.setCreatetime(now);
		token.setLosetime(DateUtils.addHours(now, DefaultValue.TOKEN_DEFAULT_TIME));
		token.setTokenstr(MD5Util.md5(username, String.valueOf(System.currentTimeMillis())));// MD5生成token
		tokenMapper.insert(token);
		return token;
	}

	/**
	 * 根据Token失效时间进行是否更新，如果当前时间距离token的失效时间小于移动端token的更新时间就更新
	 * 
	 * @param token
	 * @return
	 */
	private Token updateTokenByTokenLoseTime(Token token) {
		Date losetime = token.getLosetime();// 预计失效时间
		Date warnTime = DateUtils.addHours(new Date(), DefaultValue.TOKEN_UPDATE_TIME);// 当前+失效范围
		if (warnTime.before(losetime)) {
			return token;
		}

		Date now = new Date();
		token.setCreatetime(now);
		token.setLosetime(DateUtils.addHours(now, DefaultValue.TOKEN_DEFAULT_TIME));
		token.setTokenstr(MD5Util.md5(token.getUsername(), String.valueOf(System.currentTimeMillis())));// MD5生成token
		tokenMapper.updateByPrimaryKeySelective(token);
		return token;
	}

	@Override
	public void deleteInvalidToken() {
		TokenExample example = new TokenExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andLosetimeLessThan(new Date());
		tokenMapper.deleteByExample(example);
	}

}
