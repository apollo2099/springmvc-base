package com.base.modules.sys.user.controller;

import com.base.common.utils.PageInfo;
import com.base.controller.BaseController;
import com.base.modules.sys.dto.SysUser;
import com.base.modules.sys.user.service.SysUserService;
import com.base.utils.JSONUtils;
import com.base.utils.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录操作类
 * Created by liaoxj on 2016/12/26.
 */
@Controller
@RequestMapping("/user")
public class SysUserController extends BaseController {
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
        System.out.println(JSONUtils.jsonToString(enumeration));

        PageInfo pageInfo = this.getPageInfo();
        sysUser.setPageInfo(pageInfo);
        try {
            pageInfo = sysUserService.pageList(sysUser);
            System.out.print(JSONUtils.jsonToString(pageInfo));
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    /**
     * 退出当前登录用户
     * @return
     */
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
    @ResponseBody
    public String register(SysUser sysUser){
    	try {
            sysUser.setStatus(1);
            sysUser.setIp("127.0.0.1");
            sysUser.setLastLogin("");
			Boolean isFlag = sysUserService.register(sysUser);

            Map<String,Object> result = new HashMap<String,Object>();
            result.put("status",isFlag);
            result.put("msg","操作成功");
            return JSONUtils.jsonToString(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }


    /**
     * 跳转到新增用户页面
     * @return
     */
    @RequestMapping(value ="/add",method =RequestMethod.GET)
    public String add(){
        return "/sys/user/add";
    }

    /**
     * 调转到编辑用户页面
     * @return
     */
    @RequestMapping(value ="/edit/{userId}",method =RequestMethod.GET)
    public ModelAndView edit(@PathVariable("userId")Integer userId){
        this.getRequest().getParameter("userId");
        try {
            SysUser sysUser = sysUserService.findUserById(userId);

            ModelAndView mv = this.getModelAndView();
            mv.addObject("sysUser", sysUser);
            mv.setViewName("/sys/user/edit");
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 更新系统用户信息
     * @param sysUser
     * @return
     */
    @RequestMapping(value ="/edit",method =RequestMethod.POST)
    @ResponseBody
    public String edit(SysUser sysUser){
        try {
            Boolean isFlag = sysUserService.update(sysUser);

            Map<String,Object> result = new HashMap<String,Object>();
            result.put("status",isFlag);
            result.put("msg","操作成功");
            return JSONUtils.jsonToString(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 调转到用户详情页面
     * @return
     */
    @RequestMapping(value ="/detail/{userId}",method =RequestMethod.GET)
    public ModelAndView detail(@PathVariable("userId")Integer userId){
        try {
            SysUser sysUser = sysUserService.findUserById(userId);

            ModelAndView mv = this.getModelAndView();
            mv.addObject("sysUser", sysUser);
            mv.setViewName("/sys/user/detail");
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 删除用户信息
     * @return
     */
    @RequestMapping(value ="/delete/{userId}",method =RequestMethod.POST)
    @ResponseBody
    public String delete(@PathVariable("userId")Integer userId){
        try {
            Boolean isFlag = false;
            SysUser sysUser = sysUserService.findUserById(userId);
            if(ObjectUtils.isNotEmpty(sysUser)){
                sysUser.setStatus(-1);
                isFlag = sysUserService.updateStatus(sysUser);
            }

            return JSONUtils.jsonToString(isFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
