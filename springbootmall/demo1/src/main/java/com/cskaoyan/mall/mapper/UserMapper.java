package com.cskaoyan.mall.mapper;


import com.cskaoyan.mall.bean.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {


    Admin isAdminExistByNameAndPassword(@Param("account") String account, @Param("pwd") String pwd);

    List<Admin> queryAllAdmins();
}
