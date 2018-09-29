package userprofile.bean;

import com.alibaba.fastjson.JSON;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@DynamicUpdate
@DynamicInsert
public class Rectab{
    //自增ID


    @Id
    @GeneratedValue
    private Integer rectabid;

    @Column(name="movieid",insertable=false,updatable=false)
    private Integer movieid;

    private Integer userid;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "movieid",referencedColumnName = "movieid")
    private Movie movie;

    public Integer getRectabid(){
        return rectabid;
    }

    public void setRectabid(Integer rectabid){
        this.rectabid=rectabid;
    }

    public Integer getMovieid(){
        return movieid;
    }

    public void setMovieid(Integer movieid){
        this.movieid=movieid;
    }

    public Integer getUserid(){
        return userid;
    }

    public void setUserid(Integer userid){
        this.userid=userid;
    }


    public Movie getMovie(){
        return movie;
    }

    public void setMovie(Movie movie){
        this.movie=movie;
    }




    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
