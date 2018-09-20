package com.movie1.repository;

import com.movie1.bean.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    /**
     * 根据moviename查询
     * @param moviename
     * @return
     */
    public List<Movie> findByMoviename(String moviename);

    /**
     * 根据moviename查询
     * @param moviename
     * @return
     */
    List<Movie> findByMovienameLike(String moviename);

    /**
     *
     * 根据movieid查询
     */

    List<Movie> findByMovieid(Integer movieid);
}
