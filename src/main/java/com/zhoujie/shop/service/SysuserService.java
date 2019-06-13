package com.zhoujie.shop.service;

import com.zhoujie.shop.pojo.Sysuser;

import java.util.List;

public interface SysuserService {

    List<Sysuser> findAll();

    Sysuser login(String loginName, String password);
}
