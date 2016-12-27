package com.base.modules.sys.user.controller;

import com.base.modules.sys.dto.SysUser;
import com.base.modules.sys.user.service.SysUserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * 用户登录操作类
 * Created by liaoxj on 2016/12/26.
 */
@Controller
@RequestMapping("/user")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/tologin")
    public String tologin() {
        return "/login";
    }


    /**
     * 用户登录
     * @param sysUser
     * @return
     */
    @RequestMapping("/login")
    public String login(SysUser sysUser) {
        try {
            Boolean isLoginFlag = sysUserService.login(sysUser);
            if(isLoginFlag){
                System.out.print("登录成功");
                return "/index";
            }else{
                System.out.print("登录失败");
                return "/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/login";
    }
    
    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping(value ="/register",method =RequestMethod.GET)
    public String register(){
    	System.out.print("跳转注册成功");
    	 return "/register";
    }
    
    /**
     * 管理用户注册
     * @param sysUser
     * @return
     */
    @RequestMapping(value ="/register",method =RequestMethod.POST)
    public String register(SysUser sysUser){
    	try {
            sysUser.setStatus("1");
            sysUser.setIp("127.0.0.1");
            sysUser.setLastLogin("");
			Boolean isFlag = sysUserService.register(sysUser);
			if(isFlag){
                System.out.print("注册成功");
                return "/login";
            }else{
                System.out.print("注册失败");
                return "/register";
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "/register";
    }
}
