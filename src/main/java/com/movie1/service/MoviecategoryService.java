package com.movie1.service;

import com.movie1.bean.Moviecategory;
import com.movie1.bean.User;
import com.movie1.repository.MoviecategoryRepository;
import com.movie1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service

public class MoviecategoryService {

    @Autowired
    private MoviecategoryRepository moviecategoryRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Moviecategory> searchTypeList(int categoryid){
        List<Moviecategory> list = null;
        list = moviecategoryRepository.findByCategoryid(categoryid);
        return list;

    }

    public List<User> searchUsername(int userid){

        List<User> user = null;
        user = userRepository.findByUserid(userid);
        return user;
    }


}
