package com.movie1.controller;

import com.movie1.bean.Alstab;
import com.movie1.bean.Rectab;
import com.movie1.bean.Review;
import com.movie1.bean.User;
import com.movie1.common.exception.CommonException;

import com.movie1.service.AlstabService;
import com.movie1.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController

@RequestMapping("/profile")
public class ProfileController extends BaseController{
    @Autowired
    private ProfileService profileservice;

    @Autowired
    private AlstabService alstabService;

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


    //添加一条评论
    @PostMapping("/review")
    public Map<String, Object> addReview(final ServletRequest req,@RequestParam("movieid") int movieid, @RequestParam("content") String content,@RequestParam("star") Double star){
        Object claims = req.getAttribute("claims");
        if(claims == null) {
            throw new CommonException(401,"用户token验证失败，请重新登录");
        }
        Object userid = req.getAttribute("userid");
        Review review = profileservice.creatReview((int)userid,movieid,content,star);

        return handleResponseData(0, review);
    }

    //添加一条收藏
    @RequestMapping("/rectab")
    public Map<String,Object> addRectab(final ServletRequest req,@RequestParam("movieid") int movieid){
        Object claims = req.getAttribute("claims");
        if(claims == null) {
            throw new CommonException(401,"用户token验证失败，请重新登录");
        }
        Object userid = req.getAttribute("userid");
        Rectab rectab = profileservice.createRectab((int)userid,movieid);
        return handleResponseData(0,rectab);
    }

    @RequestMapping("/ifLikeMovie")
    public Map<String,Object> getLikeStatus(final ServletRequest req,@RequestParam("movieid") int movieid){
        Object claims = req.getAttribute("claims");
        if(claims == null) {
            throw new CommonException(401,"用户token验证失败，请重新登录");
        }
        Object userid = req.getAttribute("userid");
        Boolean status = profileservice.MovieLikeStatus(movieid, (int)userid);
        return handleResponseData(0, status);
    }

    /**
     *
     * @return
     */
    @PostMapping("/getRecommend")
    public Map<String, Object> getRecommend(final ServletRequest req) {
        final HttpServletRequest request = (HttpServletRequest) req;
        Object claims = req.getAttribute("claims");
        if (claims == null) {
            throw new CommonException(401, "用户token验证失败，请重新登录");
        }
        Object userid = req.getAttribute("userid");
        Map<String,Object> info = new HashMap<String,Object>();
        List<Alstab> list = alstabService.getUserRecommendMovie((int)userid);
        info.put("movies", list);
        return handleResponseData(0, info);
    }
}
