/*
 * Powered By [dwz4j-framework]
 * Web Site: http://j-ui.com
 * Google Code: http://code.google.com/p/dwz4j/
 * Generated 2012-09-10 08:51:33 by code generator
 */
package com.base.web.user;



import com.base.web.user.persistence.beans.SysUser;







public class User  {
	private static final long serialVersionUID = 1L;
	private SysUser sysUser;

	/* generateConstructor */
	public User() {
		this.sysUser = new SysUser();
		
	}

	public User(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public SysUser getSysUser() {
		return this.sysUser;
	}

	public Integer getId() {
		return this.sysUser.getId();
	}

	public void setId(Integer id) {
		this.sysUser.setId(id);
	}

}
