package com.movie1.service;


import com.movie1.bean.Rectab;
import com.movie1.bean.Review;
import com.movie1.bean.User;
import com.movie1.repository.RectabRepository;
import com.movie1.repository.ReviewRepository;
import com.movie1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<User> findAll() {
        return userRepository.findAll();
    }


    //根据userid查找到收藏列表
    public List<Rectab> profileGetRectabList(int userid){
        List<Rectab> rectab = null;
        rectab = rectabRepository.findByUseridOrderByRectabidDesc(userid);
        return rectab;

    }

    public List<Review> profileGetReviewList(int userid){
        List<Review> review =null;
        review = reviewRepository.findByUserid(userid);
        return review;
    }


}
