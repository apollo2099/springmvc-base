package com.base.modules.sys.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.common.dao.BaseDAO;


@Service("userService")
public class UserService {

	private static final String sqlMap ="UserMapper.";
	@Autowired
	private BaseDAO baseDAO;
	
	public Map findByUsername(String username){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map mp = (Map)  baseDAO.select(sqlMap+"findByUsername",username);
		return mp;
	}
}
