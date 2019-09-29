package com.cskaoyan.mall.controller;


import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.Result;
import com.cskaoyan.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
//@CrossOrigin(methods ={RequestMethod.GET,RequestMethod.POST,RequestMethod.OPTIONS})
@RequestMapping("/api/admin/admin")
public class AdminCotroller {
    @Autowired
    UserService userService;
//    @CrossOrigin
    //跨域的几个问题
    //第一:option请求的处理,第二接收和返回的处理
    //第一个使用Config的形式解决了,第二个需要注意的地方是@RestController默认加的是响应体转json,所以这里接收前端的数据需要用@RequestBody
    //@RequestBody用来接收单个的bean,@RequestParam用来接收多个参数
    //第三个需要注意的地方是,之前使用的Gson不需要返回的对象的toString方法,但是这里的@ResponseBody会调用返回对象的toString方法,所以需要一个
    //toString方法
    @RequestMapping("/login")
    public Result adminLogin(@RequestBody Admin admin, HttpServletRequest request){
        boolean isAdminExist=userService.adminLogin(admin);
        //之前这里没有对admin做判断空校验.
        if (admin==null||admin.getAccount()==null||admin.getPwd()==null){
            return null;
        }
        if(isAdminExist){
            HttpSession session = request.getSession();
            session.setAttribute("username",admin.getAccount());
            String id = session.getId();
            System.out.println("登录的session===id"+id);
            Result result = new Result();
            admin.setName(admin.getAccount());
            admin.setToken(admin.getPwd());
            result.setData(admin);
            return result;
        }
        return null;
    }
    @RequestMapping("/allAdmins")
    public Result queryAllAdmins(HttpServletRequest request){
        List<Admin> list=userService.queryAllAdmins();
        Result result = new Result();
        result.setData(list);
        HttpSession session = request.getSession();
        String id = session.getId();
        System.out.println("进入界面的session===id"+id);
        String username = (String) session.getAttribute("username");
        System.out.println(username);
        return result;
    }
}
