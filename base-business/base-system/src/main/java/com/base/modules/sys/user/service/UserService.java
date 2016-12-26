package com.base.modules.sys.user.service;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.common.dao.BaseDao;


@Service("userService")
public class UserService {

	private static final String sqlMap ="com.base.persistence.UserMapper.";
//	@Autowired
//	private UserMapper userMapper;

	@Autowired
	private BaseDao baseDao;


	public Map findByUsername(String username) throws Exception {
		//Map mp = (Map) baseDao.select(sqlMap+"findByUsername",username);
		Map mp = baseDao.findForMap(sqlMap+"findByUsername", username, "id", null);
		//Map mp2 = (Map) userMapper.findByUsername(username);
		return mp;
	}
}
