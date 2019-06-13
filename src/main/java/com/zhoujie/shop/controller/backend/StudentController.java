package com.zhoujie.shop.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/backend/student")
public class StudentController {

    @RequestMapping("/findAll")
    public String findAll(){

        return "studentManager";
    }

}
