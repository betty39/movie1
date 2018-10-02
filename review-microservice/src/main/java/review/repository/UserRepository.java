package review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import review.bean.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 根据userid查询
     * @param userid
     * @return
     */
    public List<User> findByUserid(int userid);
}
