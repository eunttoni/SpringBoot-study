package com.fastcampus.admin.controller.page;

import com.fastcampus.admin.service.AdminMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

// TODO Front 와 연동 하기 위한 page controller 를 작성하세요
@Controller
@RequestMapping(path={"/","/pages/student"})
public class StudentPageController {

    @Autowired
    private AdminMenuService adminMenuService;

    @RequestMapping(path = {""})
    public ModelAndView index() {
        return new ModelAndView("/pages/student/student")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "student")
                ;
    }

    @RequestMapping(path = {"/view"})
    public ModelAndView studentView() {
        return new ModelAndView("/pages/student/view")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "student")
                ;
    }
}
