package com.movie1.repository;

import com.movie1.bean.Rectab;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface RectabRepository extends JpaRepository<Rectab,Integer> {
    //根据userid查询该用户的喜欢电影list
    public List<Rectab> findByUseridOrderByRectabidDesc(Integer userid);

    /**
     * 根据movieid 与 userid查找
     * @param movieid
     * @param userid
     * @return
     */
    public List<Rectab> findByMovieidAndUserid(int movieid, int userid);
}
