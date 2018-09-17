package com.movie1.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.movie1.common.exception.CommonException;

@RestController

public class HelloController {
    @GetMapping("/")
    public String sayHello(){
        return "hello spring boot";
    }

    @GetMapping("/error")
    public String error(){
        throw new CommonException(401, "无token，请重新登录");
    }
}
