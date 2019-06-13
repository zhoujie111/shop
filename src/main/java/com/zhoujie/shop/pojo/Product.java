package com.zhoujie.shop.pojo;

/**
 * 产品 实体类
 * todo 某一个产品属于哪一个产品类型
 */
public class Product {
    private Integer id;//产品编号id
    private String name;//产品名称
    private Double price;//产品单价
    private String info;//产品说明
    private String image;//产品图片

    //product_type_id外键 product_type id主键  产品所属类型
    private ProductType productType;//********************************与另外一张表ProductType关联

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
