package com.zhoujie.shop.service;

import com.zhoujie.shop.common.exception.ProductTypeExistException;
import com.zhoujie.shop.pojo.ProductType;

import java.util.List;

public interface ProductTypeService {

    List<ProductType> findAll();

    void insert(String name) throws ProductTypeExistException;


    ProductType findById(int id);

    void updateByName(int id, String name);

    void removeById(int id);

    void modifyStatus(int id);
}
