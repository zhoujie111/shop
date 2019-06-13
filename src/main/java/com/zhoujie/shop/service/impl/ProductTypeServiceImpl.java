package com.zhoujie.shop.service.impl;

import com.zhoujie.shop.common.exception.ProductTypeExistException;
import com.zhoujie.shop.dao.ProductTypeDao;
import com.zhoujie.shop.pojo.ProductType;
import com.zhoujie.shop.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeDao productTypeDao;

    @Override
    @Transactional
    public List<ProductType> findAll() {

        List<ProductType> productTypes = productTypeDao.findAll();
        return  productTypes;
    }

    @Override
    public void insert(String name) throws ProductTypeExistException {
        //根据name查询
        ProductType productType = productTypeDao.selectByName(name);
        if(productType!=null){
            throw new ProductTypeExistException("该产品类型已经存在！");
        }
        productTypeDao.insert(name,1);
    }



    @Override
    public ProductType findById(int id) {
        ProductType productType = productTypeDao.selectById(id);
        return productType;
    }

    @Override
    public void updateByName(int id, String name) {
        productTypeDao.updateById(id,name);
    }

    @Override
    public void removeById(int id) {
        productTypeDao.deleteById(id);
    }

    @Override
    public void modifyStatus(int id) {
        ProductType productType = findById(id);
        int status = productType.getStatus();
        productTypeDao.modifyStatusById(id,status);
    }


}
