package com.base.modules.sys.user.service;
import com.base.common.dao.BaseDao;
import com.base.common.utils.PageInfo;
import com.base.modules.sys.dto.SysUser;
import com.base.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liaoxj on 2016/12/29.
 */
@Service("sysUserService")
public class SysUserService{

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
        int num = baseDao.save(sqlMap+"insert", sysUser);
        if(ObjectUtils.isNotEmpty(num)){
            return true;
        }
        return false;
    }

    /**
     * 用户信息更新
     * @param sysUser
     * @return
     * @throws Exception
     */
    public Boolean update(SysUser sysUser) throws Exception{
        int num = baseDao.update(sqlMap+"update", sysUser);
        if(ObjectUtils.isNotEmpty(num)){
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
            if(sysUser.getPassword().equals(result.getPassword())){
                return result;
            }
        }
        return null;
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


    /**
     * 查询用户分页信息（dataTable）
     * @param page
     * @param sysUser
     * @return
     */
    public PageInfo<SysUser> pageList(SysUser sysUser)throws Exception {
        PageInfo<SysUser> page = sysUser.getPageInfo();
        // 查询符合条件的条数
        int totalNum = baseDao.findForObject(sqlMap+"count", sysUser);
        // 执行分页查询
        List<SysUser> records =baseDao.findForList(sqlMap+"list",sysUser);
        if(ObjectUtils.isEmpty(page)){
            page = new PageInfo<SysUser>();
        }
        page.setData(records);
        page.setRecordsTotal(totalNum);
        page.setRecordsFiltered(totalNum);
        return page;
    }



}
