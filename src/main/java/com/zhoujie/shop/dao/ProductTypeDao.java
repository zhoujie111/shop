package com.zhoujie.shop.dao;

import com.zhoujie.shop.common.exception.ProductTypeExistException;
import com.zhoujie.shop.pojo.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductTypeDao {

    List<ProductType> findAll();

    void insert(@Param("name") String name, @Param("status") int status) throws ProductTypeExistException;

    ProductType selectByName(String name);

    ProductType selectById(int id);

    void updateById(@Param("id") int id,@Param("name") String name);

    /**
     * 根据前台传递的name获取id
     */
    int selectIdByName(String name);

    void deleteById(int id);

    void modifyStatusById(@Param("id") int id,@Param("status") int status);
}
