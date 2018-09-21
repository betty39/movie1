package com.movie1.repository;


import com.movie1.bean.Review;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface ReviewRepository extends JpaRepository<Review,Integer> {

    public List<Review> findByUserid(int userid);

    /**
     * 根据movieid查询
     * @param movieid
     * @return
     */
    public List<Review> findByMovieid(int movieid);

    /**
     * 根据movieid查询评论逆序
     * @param movieid
     * @return
     */
    public List<Review> findByMovieidOrderByReviewidDesc(int movieid);

    public List<Review> findByUseridOrderByReviewidDesc(int userid);

}
