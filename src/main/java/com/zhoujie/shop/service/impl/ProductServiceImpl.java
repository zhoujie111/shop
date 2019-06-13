package com.zhoujie.shop.service.impl;

import com.zhoujie.shop.common.exception.ProductNotExistException;
import com.zhoujie.shop.dao.ProductDao;
import com.zhoujie.shop.dto.ProductDto;
import com.zhoujie.shop.pojo.Product;
import com.zhoujie.shop.pojo.ProductType;
import com.zhoujie.shop.service.ProductService;
import com.zhoujie.shop.util.StringUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.*;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> selectAll() {
        List<Product> productList = productDao.findAll();
        return productList;
    }

    @Override
    public void add(ProductDto productDto) throws FileUploadException {

        //todo 将file保存到服务器指定位置
        String fileName = StringUtils.renameFileName(productDto.getFileName());
        String filePath = productDto.getUploadPath()+ File.separator+fileName;
        try {
            StreamUtils.copy(productDto.getInputStream(),new FileOutputStream(filePath));
        } catch (IOException e) {
            throw new FileUploadException("文件上传失败"+e.getMessage());
        }

        //todo 将数据保存到数据库 dto<pojo
        Product product = new Product();
        try {
            PropertyUtils.copyProperties(product,productDto);
            product.setImage(filePath);
            ProductType productType = new ProductType();
            productType.setId(productDto.getProductTypeId());
            product.setProductType(productType);
            productDao.insert(product);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean checkName(String name) {
        Product product=productDao.selectByName(name);
        if(product!=null){
            return false;
        }
        return true;
    }

    @Override
    public Product findById(int id) throws ProductNotExistException {
        //根据id查找产品
        Product product = productDao.findById(id);
        if(product!=null){
            return product;
        }
        throw new ProductNotExistException("id为："+id+"的产品不存在");
    }

    /**
     * 获取图片，写到输出流中
     * @param path
     * @param outputStream
     */
    @Override
    public void getImage(String path, OutputStream outputStream) {
        try {
            StreamUtils.copy(new FileInputStream(path),outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
