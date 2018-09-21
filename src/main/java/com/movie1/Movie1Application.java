package com.movie1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import java.util.*;
import com.movie1.common.jwt.*;

@SpringBootApplication()
@EnableJpaRepositories("com.movie1.repository")
public class Movie1Application {

    public static void main(String[] args) {
        SpringApplication.run(Movie1Application.class, args);
    }
    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        //添加需要拦截的url
        List<String>  urlPatterns = new ArrayList();
        urlPatterns.add("/user/resetPw");
        urlPatterns.add("/profile/lists");
        urlPatterns.add("/profile/review");
        urlPatterns.add("/profile/rectab");
        urlPatterns.add("/profile/ifLikeMovie");
        urlPatterns.add("/profile/getRecommend");
        registrationBean.addUrlPatterns(urlPatterns.toArray(new String[urlPatterns.size()]));
        return registrationBean;
    }
}
