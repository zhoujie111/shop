package com.zhoujie.shop.service.impl;

import com.zhoujie.shop.dao.CustomerDao;
import com.zhoujie.shop.pojo.Customer;
import com.zhoujie.shop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<Customer> findAll() {
        return customerDao.selectAll();
    }

    @Override
    public void deleteById(int id) {
        customerDao.deleteById(id);
    }
}
