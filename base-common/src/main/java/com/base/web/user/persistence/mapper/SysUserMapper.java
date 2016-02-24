package com.base.web.user.persistence.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.base.persistence.BaseConditionVO;
import com.base.web.user.persistence.beans.SysUser;


@Repository
public interface SysUserMapper {

	SysUser findByUsername(String username);
	
	SysUser findByVerifyCode(String verifyCode);
	
	Integer isUniqueUsername(@Param("id") Integer id, @Param("username") String username);

	void updateStatus(@Param("id") int id, @Param("status") String userStatus,
			@Param("updateDate") Date updateDate);
	
	// 查询
	List<SysUser> findPageBreakByCondition(BaseConditionVO vo, RowBounds rb);

	Integer findNumberByCondition(BaseConditionVO vo);
}
