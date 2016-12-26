package com.base.modules.sys.user.service;

import com.base.common.dao.BaseDao;
import com.base.modules.sys.dto.SysUser;
import com.base.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统用户服务类
 * Created by liaoxj on 2016/12/26.
 */
@Service("sysUserService")
public class SysUserService {
    private static final String sqlMap ="com.base.persistence.SysUserMapper.";

    @Autowired
    private BaseDao baseDao;

    /**
     * 用户登录
     * @param sysUser
     * @return
     * @throws Exception
     */
    public Boolean login(SysUser sysUser) throws Exception {
        Boolean isLoginFlag = false;
        SysUser result = baseDao.findForObject(sqlMap+"findByUserName",sysUser);
        if(ObjectUtils.isNotEmpty(result)){
            isLoginFlag = true;
        }
        return isLoginFlag;
    }
    
    /**
     * 用户注册
     * @param sysUser
     * @return
     * @throws Exception
     */
    public Boolean register(SysUser sysUser) throws Exception{
    	int num = baseDao.save(sqlMap+"findByUserName", sysUser);
    	if(num>0){
    		return true;
    	}
    	return false;
    }
    
    /**
     * 找回用户密码
     * @param sysUser
     * @return
     * @throws Exception
     */
    public SysUser findPassword(SysUser sysUser) throws Exception{
        SysUser result = baseDao.findForObject(sqlMap+"findByUserName",sysUser);
        if(ObjectUtils.isNotEmpty(result)){
            if(){
            	
            }
        }
    }
    
    /**
     * 根据用户ID查询用户详情
     * @param userId
     * @return
     * @throws Exception
     */
    public SysUser findUserById(Integer userId) throws Exception{
    	SysUser sysUser = baseDao.findForObject(sqlMap+"findByUserId", userId);
    	return sysUser;
    }
    
    
    
    
}
