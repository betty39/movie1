package review.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import review.bean.Movie;
import review.bean.Review;
import review.bean.User;
import review.common.exception.CommonException;
import review.repository.MovieRepository;
import review.repository.ReviewRepository;
import review.repository.UserRepository;

import java.util.*;

@Transactional
@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;

    //根据userid查找到个人评论列表
    public List<Review> profileGetReviewList(int userid){
        List<Review> review =null;
        review = reviewRepository.findByUseridOrderByReviewidDesc(userid);
        return review;
    }

    //添加一条评论
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
