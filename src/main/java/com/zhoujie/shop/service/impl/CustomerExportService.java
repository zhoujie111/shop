package com.zhoujie.shop.service.impl;

import com.zhoujie.shop.pojo.Customer;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 导出excel的业务类
 */
@Service
public class CustomerExportService {

    public HSSFWorkbook export(List<Customer> list) {
        String[] excelHeader = {"姓名","电话","地址"};
        //获取HSSFWorkbook 对象
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("customer");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }

        for(int i = 0; i < list.size(); i++){
            row = sheet.createRow(i + 1);
            Customer customer = list.get(i);
            row.createCell(0).setCellValue(customer.getName());
            row.createCell(1).setCellValue(customer.getPhone());
            row.createCell(2).setCellValue(customer.getAddress());
        }
        return wb;
    }

}
