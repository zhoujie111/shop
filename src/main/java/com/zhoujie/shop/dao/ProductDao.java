package com.zhoujie.shop.dao;

import com.zhoujie.shop.pojo.Product;

import java.util.List;

public interface ProductDao {

    /**
     * 查询所有的产品 所有的数据记录数目 <<<<< List<Product>
     * @return
     */
    List<Product> findAll();

    /**
     * 添加商品
     * @param product
     */
    void insert(Product product);

    Product selectByName(String name);

    Product findById(int id);
}
