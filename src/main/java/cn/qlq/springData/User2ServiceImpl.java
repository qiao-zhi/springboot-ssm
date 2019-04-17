package cn.qlq.springData;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class User2ServiceImpl implements User2Service {
	@Autowired
	private UserDao userDao;

	@Override
	public void save(User2 user2) {
		userDao.save(user2);
	}

}
