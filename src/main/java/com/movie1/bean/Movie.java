package com.movie1.bean;

import com.alibaba.fastjson.JSON;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@DynamicUpdate
@DynamicInsert
public class Movie {
    //自增ID
    @Id
    @GeneratedValue
    private Integer movieid;

    private String moviename;

    private Double averating;

    private String director;

    private String nation;

    private Date showyear;

    private String picture;

    private Integer numrating;

    //需要声明无参数的构造函数
    public Movie(){  }

    public Integer getMovieid() {
        return movieid;
    }

    public String getMoviename() {
        return moviename;
    }

    public Integer getNumrating() {
        return numrating;
    }

    public Date getShowyear() {
        return showyear;
    }

    public String getPicture() {
        return picture;
    }

    public Double getAverating() {
        return averating;
    }

    public String getDirector() {
        return director;
    }

    public String getNation() {
        return nation;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
