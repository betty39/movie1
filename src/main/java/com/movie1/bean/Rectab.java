package com.movie1.bean;

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

    @Column(name="userid",insertable=false,updatable=false)
    private Integer userid;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "userid",referencedColumnName = "userid")
    private User user;

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

    public void setUserid(){
        this.userid=userid;
    }




    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user=user;
    }


    public Movie getMovie(){
        return movie;
    }

    public void setMovie(Movie movie){
        this.movie=movie;
    }



  /*  @ManyToOne()
    private Set<Movie> movieList;
    public Set<Movie> getMovie() {
        return movieList;
    }

    public void setMovie(Set<Movie> movieList) {
        this.movieList = movieList;
    }*/

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
