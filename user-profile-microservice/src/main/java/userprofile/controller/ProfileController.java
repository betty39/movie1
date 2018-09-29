package userprofile.controller;

import userprofile.bean.Rectab;
import userprofile.bean.Review;
import userprofile.common.exception.CommonException;

import userprofile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
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
        List<Rectab> rectab =profileservice.profileGetRectabList(Integer.parseInt(String.valueOf(userid)));
        List<Review> review =profileservice.profileGetReviewList(Integer.parseInt(String.valueOf(userid)));
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
        Review review = profileservice.creatReview(Integer.parseInt(String.valueOf(userid)),movieid,content,star);

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
        Rectab rectab = profileservice.createRectab(Integer.parseInt(String.valueOf(userid)),movieid);
        return handleResponseData(0,rectab);
    }

    /**
     * 是否收藏过某电影
     * @param req
     * @param movieid
     * @return
     */
    @RequestMapping("/ifLikeMovie")
    public Map<String,Object> getLikeStatus(final ServletRequest req,@RequestParam("movieid") int movieid){
        Object claims = req.getAttribute("claims");
        if(claims == null) {
            throw new CommonException(401,"用户token验证失败，请重新登录");
        }
        Object userid = req.getAttribute("userid");
        Boolean status = profileservice.MovieLikeStatus(movieid, (Integer.parseInt(String.valueOf(userid))));
        return handleResponseData(0, status);
    }

    /**
     * 获取某电影的评论列表
     * @param movieid
     * @return
     */
    @RequestMapping("/movieReviewLists")
    public Map<String,Object> getLikeStatus(@RequestParam("movieid") int movieid){
        Map<String,Object> listreview = profileservice.reviewList(movieid);
        return handleResponseData(0, listreview);
    }
}
