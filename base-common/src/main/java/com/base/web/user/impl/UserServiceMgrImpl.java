package com.base.web.user.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.apache.ibatis.session.RowBounds;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.base.persistence.BaseConditionVO;
import com.base.web.user.User;
import com.base.web.user.UserServiceMgr;
import com.base.web.user.persistence.beans.SysUser;
import com.base.web.user.persistence.mapper.SysUserMapper;

@Transactional(rollbackFor = Exception.class)
@Service(UserServiceMgr.SERVICE_NAME)
public class UserServiceMgrImpl implements UserServiceMgr {
	
	@Autowired
	private SysUserMapper userMapper;
	




	public List<User> searchUser(BaseConditionVO vo) {
		List<User> bos = new ArrayList<User>();
		RowBounds rb = new RowBounds(vo.getStartIndex(), vo.getPageSize());
		List<SysUser> pos = userMapper.findPageBreakByCondition(vo, rb);
		for (SysUser po : pos) {
			bos.add(new User(po));
		}
		return bos;
	}


	public User getUserByUsername(String username){
		SysUser po = userMapper.findByUsername(username);
		if (po == null) return null;
		return new User(po);
	}





	public void register(User user) throws Exception {
		// TODO Auto-generated method stub
		
	}





	public void sendVerifyEmail(User user) throws Exception {
		// TODO Auto-generated method stub
		
	}





	public void verify(String verifyCode) throws Exception {
		// TODO Auto-generated method stub
		
	}





	public void addUser(User user) throws Exception {
		// TODO Auto-generated method stub
		
	}





	public void updUser(User user) {
		// TODO Auto-generated method stub
		
	}





	public void delUser(int id) {
		// TODO Auto-generated method stub
		
	}





	public Integer searchUserNum(BaseConditionVO vo) {
		// TODO Auto-generated method stub
		return null;
	}





	public void activeUser(int id) {
		// TODO Auto-generated method stub
		
	}





	public void inActiveUsesr(int id) {
		// TODO Auto-generated method stub
		
	}


	public User getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
