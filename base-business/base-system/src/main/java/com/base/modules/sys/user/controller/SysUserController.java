package com.base.modules.sys.user.controller;

import com.base.modules.sys.dto.SysUser;
import com.base.modules.sys.user.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/12/26.
 */
@Controller
@RequestMapping("/user")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/login")
    public String login(SysUser sysUser) {
        try {
            Boolean isLoginFlag = sysUserService.login(username, password);
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
}
