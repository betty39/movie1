package userprofile.service;


import userprofile.bean.Movie;
import userprofile.bean.Rectab;
import userprofile.bean.Review;
import userprofile.bean.User;
import userprofile.common.exception.CommonException;
import userprofile.repository.MovieRepository;
import userprofile.repository.RectabRepository;
import userprofile.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import userprofile.repository.UserRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@Transactional
@Service
public class ProfileService {
    @Autowired
    private RectabRepository rectabRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;


    //根据userid查找到喜欢列表
    public List<Rectab> profileGetRectabList(int userid){
        List<Rectab> rectab = null;
        rectab = rectabRepository.findByUseridOrderByRectabidDesc(userid);
        return rectab;

    }

    //根据userid查找到收藏列表
    public List<Review> profileGetReviewList(int userid){
        List<Review> review =null;
        review = reviewRepository.findByUseridOrderByReviewidDesc(userid);
        return review;
    }

    //添加一条收藏

    public Review creatReview( int userid,int movieid, String content,Double star) {

        List<Movie> movieList=movieRepository.findByMovieid(movieid);
        if (movieList == null || movieList.size() == 0) {
            throw new CommonException(401, "你所评论的电影不存在");
        }
        Movie movie = movieList.get(0);
        Review review = new Review();
        review.setUserid(userid);
        review.setMovieid(movieid);
        review.setContent(content);
        review.setStar(star);
        review.setReviewtime(new Date());
        review.setMovie(movie);
        return reviewRepository.save(review);
    }


    //添加一条喜欢

    public Rectab createRectab(int userid,int movieid){
        List<Movie> movieList=movieRepository.findByMovieid(movieid);
        if (movieList == null || movieList.size() == 0) {
            throw new CommonException(401, "你所评论的电影不存在");
        }
        Movie movie = movieList.get(0);

        Rectab rectab =new Rectab();
        rectab.setUserid(userid);
        rectab.setMovieid(movieid);
        rectab.setMovie(movie);
        return rectabRepository.save(rectab);
    }

    /**
     * 获取对某个电影的喜欢状态
     * @param movieid
     * @return
     */
    public Boolean MovieLikeStatus(int movieid, int userid) {
        Boolean status = false;
        List<Rectab> info = rectabRepository.findByMovieidAndUserid(movieid, userid);
        if (info != null && info.size() != 0) {
            status = true;
        }
        return status;
    }

    /*
     * 电影详情界面评论列表
     */
    public Map<String,Object> reviewList(int movieid){
        List<Review> list = null;
        list = reviewRepository.findByMovieidOrderByReviewidDesc(movieid);
        List<Map<String, Object>> reviewList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i< list.size();i++){

            int uid = list.get(i).getUserid();
            List<User> user = userRepository.findByUserid(uid);
            String username = user.get(0).getUsername();
            Map<String, Object> review = new HashMap<String,Object>();
            review.put("star", list.get(i).getStar());
            review.put("content",list.get(i).getContent());
            review.put("username", username);
            review.put("reviewtime",list.get(i).getReviewtime());
            reviewList.add(review);
        }

        Map<String,Object> info = new HashMap<String,Object>();
        info.put("reviewList",reviewList);
        return info;
    }
}
