package com.base.web.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.common.dao.BaseDAO;
import com.base.web.user.persistence.beans.SysUser;


@Service("userService")
public class UserService {
	private static final String sqlMap ="SysUserMapper.";
	@Autowired
	private BaseDAO baseDAO;
	/**
	 * 查询新闻列表
	 * @return
	 */
	public SysUser findByUsername(String username){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		SysUser user=(SysUser) baseDAO.select(sqlMap+"findByUsername",username);
		return user;
	}
}
