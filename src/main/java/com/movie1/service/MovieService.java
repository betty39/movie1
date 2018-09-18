package com.movie1.service;

import com.movie1.bean.Movie;
import com.movie1.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    /*
     * 搜索
     */
    public List<Movie> search(String moviename) {
        List<Movie> list = null;
        list = movieRepository.findByMovienameLike("%"+moviename+"%");
        if (list == null || list.size() == 0) {
            // 返回登录失败
            return null;
        }
        return list;
    }

}
