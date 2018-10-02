package collect.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import collect.bean.Movie;
import collect.bean.Rectab;
import collect.bean.User;
import collect.common.exception.CommonException;
import collect.repository.MovieRepository;
import collect.repository.RectabRepository;
import collect.repository.UserRepository;

import java.util.*;

@Transactional
@Service
public class CollectService {
    @Autowired
    private RectabRepository rectabRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;


    //根据userid查找到喜欢列表
    public List<Rectab> profileGetRectabList(int userid){
        List<Rectab> rectab = null;
        rectab = rectabRepository.findByUseridOrderByRectabidDesc(userid);
        return rectab;

    }

    //添加一条喜欢

    public Rectab createRectab(int userid,int movieid){
        List<Movie> movieList=movieRepository.findByMovieid(movieid);
        if (movieList == null || movieList.size() == 0) {
            throw new CommonException(401, "你所评论的电影不存在");
        }
        Movie movie = movieList.get(0);

        Rectab rectab =new Rectab();
        rectab.setUserid(userid);
        rectab.setMovieid(movieid);
        rectab.setMovie(movie);
        return rectabRepository.save(rectab);
    }

    /**
     * 获取对某个电影的喜欢状态
     * @param movieid
     * @return
     */
    public Boolean MovieLikeStatus(int movieid, int userid) {
        Boolean status = false;
        List<Rectab> info = rectabRepository.findByMovieidAndUserid(movieid, userid);
        if (info != null && info.size() != 0) {
            status = true;
        }
        return status;
    }
}
