package com.zhoujie.shop.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StringUtils {

    //beauty.png
    public static String renameFileName(String fileName){
        int dotIndex = fileName.lastIndexOf(".");//6
        String suffix = fileName.substring(dotIndex);
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ new Random().nextInt(100)+suffix;
    }

    // todo 测试
    public static void main(String[] args) {
        String fileName = "beauty.png";
        String newfileName =StringUtils.renameFileName(fileName);
        System.out.println(newfileName);// 2019061110574088.png
    }
}
