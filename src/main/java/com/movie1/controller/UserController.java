package com.movie1.controller;

import com.movie1.service.UserService;
import com.movie1.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    /**
     * 查找所有
     * @return
     */
    @GetMapping("/lists")
    public Map<String, Object> findUserAll(){
        List<User> userList = userService.findAll();
        return handleResponseData(0, userList);
    }

    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam("username") String username, @RequestParam("password") String password){
        User user = userService.login(username, password);
        if(user == null) {
            return handleResponseData(-1,null, "用户名不存在或密码不正确");
        }
        return handleResponseData(0, user);
    }

    /**
     * 注册
     * @return
     */
    @PostMapping("/register")
    public Map<String, Object> register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email){
        User user = userService.register(username, password, email);
        return handleResponseData(0, user);
    }
}
