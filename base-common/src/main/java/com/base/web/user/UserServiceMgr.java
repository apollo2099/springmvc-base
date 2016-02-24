package com.base.web.user;

import java.util.List;

import com.base.persistence.BaseConditionVO;



public interface UserServiceMgr {
	String SERVICE_NAME = "userServiceMgr";
	
	/**
	 * 前台用户注册
	 * 
	 * @param user
	 * @throws ServiceException 
	 */
	void register(User user) throws Exception;

	void sendVerifyEmail(User user) throws Exception;
	
	void verify(String verifyCode) throws Exception;

	/**
	 * 后台添加用户
	 * 
	 * @param user
	 */
	void addUser(User user) throws Exception;

	void updUser(User user);

	User getUser(int id);

	User getUserByUsername(String username);

	void delUser(int id);

	List<User> searchUser(BaseConditionVO vo);

	Integer searchUserNum(BaseConditionVO vo);

	/**
	 * 激活一个用户
	 * 
	 * @param id
	 */
	void activeUser(int id);

	/**
	 * 禁用一个用户
	 * 
	 * @param id
	 */
	void inActiveUsesr(int id);

}
