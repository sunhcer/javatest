package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean adminLogin(Admin admin) {
        Admin admin1=userMapper.isAdminExistByNameAndPassword(admin.getAccount(),admin.getPwd());
        if (admin1!=null){
            return true;
        }
        return false;
    }

    @Override
    public List<Admin> queryAllAdmins() {
        List<Admin> list=userMapper.queryAllAdmins();
        return list;
    }
}
