package com.movie1.repository;


import com.movie1.bean.Review;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface ReviewRepository extends JpaRepository<Review,Integer> {
    public List<Review> findByUserid(int userid);
}
