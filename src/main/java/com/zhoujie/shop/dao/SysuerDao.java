package com.zhoujie.shop.dao;

import com.zhoujie.shop.pojo.Sysuser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysuerDao {

    List<Sysuser> selectAll();

    Sysuser selectByLoginNameAndPassword(@Param("loginName") String loginName, @Param("password") String password);
}
