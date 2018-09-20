package com.movie1.repository;

import com.movie1.bean.Moviecategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviecategoryRepository extends JpaRepository<Moviecategory, Integer>{


    /**
     * 根据categoryid查询
     * @param categoryid
     * @return
     */
    public List<Moviecategory> findByCategoryid(int categoryid);

}
