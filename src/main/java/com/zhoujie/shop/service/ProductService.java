package com.zhoujie.shop.service;

import com.zhoujie.shop.common.exception.ProductNotExistException;
import com.zhoujie.shop.dto.ProductDto;
import com.zhoujie.shop.pojo.Product;
import org.apache.commons.fileupload.FileUploadException;

import java.io.OutputStream;
import java.util.List;

public interface ProductService {

    List<Product> selectAll();

    void add(ProductDto productDto) throws FileUploadException;

    boolean checkName(String name);

    Product findById(int id) throws ProductNotExistException;

    void getImage(String path, OutputStream outputStream);
}
