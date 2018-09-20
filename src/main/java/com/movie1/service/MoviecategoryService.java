package com.movie1.service;

import com.movie1.bean.Moviecategory;
import com.movie1.bean.User;
import com.movie1.repository.MoviecategoryRepository;
import com.movie1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Pageable;

@Transactional
@Service

public class MoviecategoryService {

    @Autowired
    private MoviecategoryRepository moviecategoryRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Moviecategory> searchTypeList(int categoryid, int page, int size){
        Sort sort = new Sort(Direction.DESC, "movcatid");
        Pageable pageable = new PageRequest(page, size, sort);
        List<Moviecategory> list = null;
        list = moviecategoryRepository.findByCategoryid(categoryid, pageable);
        return list;

    }

    public List<User> searchUsername(int userid){

        List<User> user = null;
        user = userRepository.findByUserid(userid);
        return user;
    }


}
