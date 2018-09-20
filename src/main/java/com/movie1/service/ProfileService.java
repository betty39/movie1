package com.movie1.service;


import com.movie1.bean.Movie;
import com.movie1.bean.Rectab;
import com.movie1.bean.Review;
import com.movie1.bean.User;
import com.movie1.common.exception.CommonException;
import com.movie1.repository.MovieRepository;
import com.movie1.repository.RectabRepository;
import com.movie1.repository.ReviewRepository;
import com.movie1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class ProfileService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RectabRepository rectabRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MovieRepository movieRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }


    //根据userid查找到喜欢列表
    public List<Rectab> profileGetRectabList(int userid){
        List<Rectab> rectab = null;
        rectab = rectabRepository.findByUseridOrderByRectabidDesc(userid);
        return rectab;

    }

    //根据userid查找到收藏列表
    public List<Review> profileGetReviewList(int userid){
        List<Review> review =null;
        review = reviewRepository.findByUserid(userid);
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



}
