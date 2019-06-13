package com.zhoujie.shop.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhoujie.shop.common.util.ResponseResult;
import com.zhoujie.shop.dao.OrderDao;
import com.zhoujie.shop.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/backend/order")
public class OrderController {

    @Autowired
    private OrderDao orderDao;

    @RequestMapping("/findAll")
    public String findAll(Integer pageNum,Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,5);
        List<Order> orderList = orderDao.findAll();
        PageInfo<Order> pageInfo = new PageInfo<Order>(orderList);
        model.addAttribute("pageInfo",pageInfo);
        return "orderManager";
    }

    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(int id){
        ResponseResult result = new ResponseResult();
        Order order = orderDao.findById(id);
        result.setStatus(1);
        result.setData(order);
        return result;
    }
}
