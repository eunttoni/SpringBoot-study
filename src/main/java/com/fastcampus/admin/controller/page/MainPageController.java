package com.fastcampus.admin.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fastcampus.admin.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;


// TODO Front 와 연동 하기 위한 page controller 를 작성하세요
@Controller
@RequestMapping(path={"/","/pages"})
public class MainPageController {

    @Autowired
    private AdminMenuService adminMenuService;

    @RequestMapping(path = {"/","/main"})
    public ModelAndView index() {
        return new ModelAndView("/pages/main")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "main")
                ;
    }

}
