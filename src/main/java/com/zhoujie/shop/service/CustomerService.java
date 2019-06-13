package com.zhoujie.shop.service;

import com.zhoujie.shop.pojo.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    void deleteById(int id);
}
