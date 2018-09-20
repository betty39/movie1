package com.movie1.controller;

import com.movie1.bean.Rectab;
import com.movie1.bean.Review;
import com.movie1.bean.User;
import com.movie1.common.exception.CommonException;

import com.movie1.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController

@RequestMapping("/profile")
public class ProfileController extends BaseController{
    @Autowired
    private ProfileService profileservice;

    //获取Profile页面需要的参数
    @PostMapping("/lists")
    public Map<String,Object> profileGetList (final ServletRequest req){
        Object claims = req.getAttribute("claims");
        if(claims == null) {
            throw new CommonException(401,"用户token验证失败，请重新登录");
        }

        Map<String,Object> info = new HashMap<String,Object>();
        Object userid = req.getAttribute("userid");
        List<Rectab> rectab =profileservice.profileGetRectabList((int)userid);
        List<Review> review =profileservice.profileGetReviewList((int)userid);
        info.put("rectab",rectab);
        info.put("review",review);
        return handleResponseData(0, info);

    }







}
