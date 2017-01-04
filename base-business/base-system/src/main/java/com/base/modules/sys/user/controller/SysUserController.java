package com.base.modules.sys.user.controller;

import com.base.common.utils.PageInfo;
import com.base.modules.sys.dto.SysUser;
import com.base.modules.sys.user.service.SysUserService;
import com.base.utils.JSONUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 用户登录操作类
 * Created by liaoxj on 2016/12/26.
 */
@Controller
@RequestMapping("/user")
public class SysUserController{
    @Resource
    private SysUserService sysUserService;


    /**
     * 用户分页查询列表页面
     * @return
     */
    @RequestMapping("/list")
    public String list(){
        return "/sys/user/list";
    }


    /**
     * 用户分页查询列表页面
     * @return
     */
    @RequestMapping("/pageList")
    @ResponseBody
    public String pageList(SysUser sysUser,HttpServletRequest request){
        Enumeration enumeration = request.getParameterNames();
        System.out.print(JSONUtils.jsonToString(enumeration));

        String sEcho = request.getParameter("sEcho");
        String start = request.getParameter("iDisplayStart");// 起始
        String length = request.getParameter("iDisplayLength");// 分页大小size
        PageInfo pageInfo = new PageInfo();
        pageInfo.setStart(Integer.parseInt(start));
        pageInfo.setLength(Integer.parseInt(length) + Integer.parseInt(start));
        pageInfo.setsEcho(Integer.parseInt(sEcho));
        sysUser.setPageInfo(pageInfo);
        try {
            pageInfo = sysUserService.pageList(sysUser);
            System.out.print(JSONUtils.jsonToString(pageInfo));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Map<String, Object> dataMap =new HashMap<String, Object>();
//        dataMap.put("iTotalRecords", pageInfo.getRecordsTotal());
//        dataMap.put("sEcho",pageInfo.get);
//        dataMap.put("iTotalDisplayRecords", userPage.getTotalRow());
//        dataMap.put("aaData", page.getPages());

        return JSONUtils.jsonToString(pageInfo);
    }


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

    public String loginout(){
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
