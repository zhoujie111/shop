package com.zhoujie.shop.dao;

import com.zhoujie.shop.pojo.Order;

import java.util.List;

public interface OrderDao {

    /**
     * 查询所有的订单信息
     * @return
     */
    List<Order> findAll();

    Order findById(int id);
}
