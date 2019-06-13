package com.zhoujie.shop.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhoujie.shop.common.util.ResponseResult;
import com.zhoujie.shop.dao.CustomerDao;
import com.zhoujie.shop.pojo.Customer;
import com.zhoujie.shop.service.CustomerService;
import com.zhoujie.shop.service.impl.CustomerExportService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("/backend/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CustomerExportService customerExportService;

    @RequestMapping("/findAll")
    public String findAll(Integer pageNum, Model model) {
        //分页设置
        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, 5);
        List<Customer> customerList = customerService.findAll();
        PageInfo<Customer> pageInfo = new PageInfo<Customer>(customerList);
        model.addAttribute("pageInfo", pageInfo);
        //所有的数据
        return "customerManager";
    }

    @RequestMapping("/exportXls")
    public void export(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Customer> customerList = customerService.findAll();
        HSSFWorkbook wb = customerExportService.export(customerList);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=customer.xls");
        OutputStream ouputStream = response.getOutputStream();
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }

    /**
     * 如何进行多条件查询？？todo
     * @return
     */
    @RequestMapping(value = "/findByParams",method = RequestMethod.POST)
    public String findByParams(Integer pageNum, Model model,Customer customer){
        System.out.println(customer.getName());
        //后台接受前台传递过来的参数，在数据库查询封装成对象
        //分页设置
        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, 5);
        List<Customer> customerList = customerDao.selectByParams(customer);
        // todo 条件查询

        PageInfo<Customer> pageInfo = new PageInfo<Customer>(customerList);
        model.addAttribute("pageInfo", pageInfo);
        //所有的数据
        return "customerManager";
    }

    @RequestMapping("/removeById")
    @ResponseBody
    public ResponseResult removeById(int id){
        ResponseResult result = new ResponseResult();
        try{
            customerService.deleteById(id);
            result.setStatus(1);
            result.setMessage("删除成功");
        }catch (Exception e){
            result.setStatus(0);
            result.setMessage("数据库删除失败");
        }
        return result;
    }

}
