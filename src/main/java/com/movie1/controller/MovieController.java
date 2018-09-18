package com.movie1.controller;

import com.movie1.bean.Movie;
import com.movie1.bean.Similartab;
import com.movie1.service.MovieService;
import com.movie1.service.SimilarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private SimilarService similarService;

    /**
     * 搜索
     * @return
     */
    @PostMapping("/serchbyname")
    public Map<String, Object> search(@RequestParam("moviename") String moviename){
        List<Movie> movies = movieService.search(moviename);

        Map<String , Object> info = new HashMap<String , Object>();
        info.put("movies", movies);
        return info;
    }

    /**
     * 搜索
     * @return
     */
    @PostMapping("/getsimilar")
    public Map<String, Object> getsimilar(@RequestParam("itemid") Integer itemid){
        List<Similartab> similars = similarService.getsimilar(itemid);

        Map<String , Object> info = new HashMap<String , Object>();
        info.put("similars", similars);
        return info;
    }
}
