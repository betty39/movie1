package com.movie1.service;

import com.movie1.bean.Movie;
import com.movie1.bean.Review;
import com.movie1.repository.MovieRepository;
import com.movie1.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ReviewRepository reviewRepository;


    /*
     * 搜索
     */
    public List<Movie> search(String moviename) {
        List<Movie> list = null;
        list = movieRepository.findFirst5ByMovienameLike("%"+moviename+"%");
        if (list == null || list.size() == 0) {
            // 返回登录失败
            return null;
        }
        return list;
    }

    /*
     * 电影详情
     */
    public List<Movie> description(int movieid){
        List<Movie> list = null;
        list = movieRepository.findByMovieid(movieid);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    /*
     * 电影详情界面评论列表
     */
    public List<Review> reviewList(int movieid){
        List<Review> list = null;
        list = reviewRepository.findByMovieid(movieid);

        return list;
    }



}
