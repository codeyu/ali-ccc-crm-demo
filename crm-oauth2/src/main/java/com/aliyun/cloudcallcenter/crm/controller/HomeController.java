package com.aliyun.cloudcallcenter.crm.controller;

import java.security.Principal;

import com.aliyun.cloudcallcenter.crm.aliyun.manager.UserManager;
import com.aliyun.cloudcallcenter.crm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author edward
 * @date 2017/11/12
 */
@Controller
public class HomeController {

    @Autowired
    private UserManager userManager;

    @RequestMapping(value = {"/home", "/"})
    public String home(Model model, Principal principal) {
        User user = userManager.findByUserName(principal.getName());
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("isAliyunBound", user.isAliyunBound());
        if (user.isAliyunBound()) {
            model.addAttribute("aliyunUserName", user.getTokePackage().getUserInfo().getPrincipalName());
            model.addAttribute("createdTime", user.getTokePackage().getCreateDate().toString());
            model.addAttribute("expiredTime", user.getTokePackage().getExpiredDate().toString());
        }
        return "home";
    }
}
