package com.zhoujie.shop.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhoujie.shop.common.exception.ProductTypeExistException;
import com.zhoujie.shop.common.util.ResponseResult;
import com.zhoujie.shop.pojo.ProductType;
import com.zhoujie.shop.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/backend/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    /**
     * 查询出后台所有的产品类型数据，返回给前端进行展示
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("/findAll")
    public String  findAll(Integer pageNum,Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,5);
        PageHelper pageHelper = new PageHelper();
        //查询出所有的产品类型数据 todo 优化
        List<ProductType> productTypes = productTypeService.findAll();
        PageInfo<ProductType> pageInfo = new PageInfo<ProductType>(productTypes);
        model.addAttribute("pageInfo",pageInfo);
        return "productTypeManager";
    }


    /**
     * 与前台ajax进行数据交互
     * 如果产品类型已经存在，提示前台
     * 如果不存在插入
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(String name){
        System.out.println(name);
        ResponseResult result = new ResponseResult();
        try {
            productTypeService.insert(name);
            result.setStatus(1);
            result.setMessage("添加成功");
        } catch (ProductTypeExistException e) {
            result.setStatus(2);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(int id){
        ResponseResult result = new ResponseResult();
        ProductType productType=productTypeService.findById(id);
        result.setStatus(1);
        result.setData(productType);
        return result;
    }


    @RequestMapping("/modifyName")
    @ResponseBody
    public ResponseResult modify(int id,String name){
        System.out.println("获取前台传递过来的值:"+name+":"+id);
        ResponseResult result = new ResponseResult();
        //调用service层方法
        productTypeService.updateByName(id,name);
        result.setStatus(1);
        result.setMessage("修改成功");
        return result;
    }

    @RequestMapping("/removeById")
    @ResponseBody
    public ResponseResult removeById(int id){
        ResponseResult result = new ResponseResult();
        productTypeService.removeById(id);
        result.setStatus(1);
        result.setMessage("删除成功");
        return result;
    }

    @RequestMapping("/modifyStatus")
    @ResponseBody
    public ResponseResult modifyStatus(int id){
        ResponseResult result = new ResponseResult();
        productTypeService.modifyStatus(id);
        result.setStatus(1);
        result.setMessage("修改状态成功");
        return result;
    }

}
