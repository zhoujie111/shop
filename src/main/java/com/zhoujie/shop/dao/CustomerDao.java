package com.zhoujie.shop.dao;

import com.zhoujie.shop.pojo.Customer;

import java.util.List;

public interface CustomerDao {

    List<Customer> selectAll();

    List<Customer> selectByParams(Customer customer);

    void deleteById(int id);
}
