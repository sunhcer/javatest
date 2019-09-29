package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Admin;

import java.util.List;

public interface UserService {

    boolean adminLogin(Admin admin);

    List<Admin> queryAllAdmins();
}
