package userprofile.repository;

import userprofile.bean.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    /**
     *
     * 根据movieid查询
     */

    List<Movie> findByMovieid(Integer movieid);
}
