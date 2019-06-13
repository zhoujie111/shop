package com.zhoujie.shop.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhoujie.shop.pojo.Sysuser;
import com.zhoujie.shop.service.SysuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/backend/sysuser")
public class SysuserController {

    @Autowired
    private SysuserService sysuserService;

    /**
     * 系统用户登录验证功能
     * 前台传递2个字段进入到后台controller
     * @return
     */
    @RequestMapping("/login")
    public String login(String loginName, String password, HttpSession session,Model model){
        Sysuser sysuser = sysuserService.login(loginName,password);
        if(sysuser!=null){
            session.setAttribute("sysuser",sysuser);
            return "main";
        }else{
            session.setAttribute("errorMsg","登陆失败");
            return "login";//转发
        }
    }

    /**
     * 登出功能
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("sysuser");
        return "login";
    }

    /**
     * 显示所有的系统用户
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(Integer pageNum, Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,5);
        List<Sysuser> sysuserList = sysuserService.findAll();
        PageInfo<Sysuser> pageInfo = new PageInfo<Sysuser>(sysuserList);
        model.addAttribute("pageInfo",pageInfo);
        return "sysuserManager";
    }
}
