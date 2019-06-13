package com.zhoujie.shop.controller.front;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("fProductController")
@RequestMapping("/front/product")
public class ProductController {

    @RequestMapping("/search")
    public String search(Integer pageNum, Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,4);
        return "front";
    }


}
