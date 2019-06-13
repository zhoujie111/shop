package com.zhoujie.shop.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhoujie.shop.common.exception.ProductNotExistException;
import com.zhoujie.shop.common.util.ResponseResult;
import com.zhoujie.shop.dto.ProductDto;
import com.zhoujie.shop.pojo.Product;
import com.zhoujie.shop.pojo.ProductType;
import com.zhoujie.shop.service.ProductService;
import com.zhoujie.shop.service.ProductTypeService;
import com.zhoujie.shop.vo.ProductVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/backend/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping("/findAll")
    public String findAll(Integer pageNum,Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,5);
        List<Product> productList = productService.selectAll();
        PageInfo<Product> pageInfo = new PageInfo<Product>(productList);
        model.addAttribute("pageInfo",pageInfo);
        return "productManager";
    }


    /**
     * 添加和修改商品之前加载商品类型
     */
    @ModelAttribute("productTypes")
    public List<ProductType> loadProductTypes(){
        List<ProductType> productTypes = productTypeService.findAll();
        return productTypes;
    }

    /**
     * 添加商品
     */
    @RequestMapping("/add")
    public Object addProduct(ProductVo productVo, HttpSession session,Model model,Integer pageNum){
        /**
         * 封装前台产品添加的相关数据vo
         */
        // todo vo <<< dto 将参数传递给service层，调用service方法进行业务处理
        try {
            ProductDto productDto = new ProductDto();
            PropertyUtils.copyProperties(productDto,productVo);
            productDto.setFileName(productVo.getFile().getOriginalFilename());
            productDto.setInputStream(productVo.getFile().getInputStream());

            String uploadPath = session.getServletContext().getRealPath("/WEB-INF/upload");
            productDto.setUploadPath(uploadPath);
            productService.add(productDto);
            model.addAttribute("successMsg","添加成功");
        } catch (Exception e) {
            model.addAttribute("errorMsg1",e.getMessage());
        }
        return "forward:findAll?pageNum="+pageNum;
    }


    @RequestMapping("/checkName")
    @ResponseBody
    public Map<String, Object> checkName(String name) {
        Map<String, Object> map = new HashMap<>();
        if (productService.checkName(name)) { //不存在，可用
            map.put("valid", true);
        } else {
            map.put("valid", false);
            map.put("message", "商品（" + name + "）已存在");
        }
        return map;
    }

    @RequestMapping("/getImage")
    public void getImage(String path, OutputStream outputStream) {
        productService.getImage(path, outputStream);
    }

    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(int id){
        System.out.println("前台传递的id为："+id);
        ResponseResult result = new ResponseResult();
        try {
            Product product = productService.findById(id);
            result.setStatus(1);
            result.setData(product);
            result.setMessage("查询成功");
        } catch (ProductNotExistException e) {
            result.setStatus(0);
            result.setMessage("查找失败！"+e.getMessage());
        }
        return result;
    }


}
