package com.zhoujie.shop.service.impl;

import com.zhoujie.shop.dao.SysuerDao;
import com.zhoujie.shop.pojo.Sysuser;
import com.zhoujie.shop.service.SysuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysuserServiceImpl implements SysuserService {

    @Autowired
    private SysuerDao sysuerDao;

    @Override
    public List<Sysuser> findAll() {
        return sysuerDao.selectAll();
    }

    @Override
    public Sysuser login(String loginName, String password) {
        return sysuerDao.selectByLoginNameAndPassword(loginName,password);

    }
}
