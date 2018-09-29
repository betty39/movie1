package userprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import java.util.*;
import userprofile.common.jwt.*;


@SpringBootApplication
@EnableDiscoveryClient
public class UserProfileMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserProfileMicroserviceApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        //添加需要拦截的url
        List<String>  urlPatterns = new ArrayList();
        urlPatterns.add("/lists");
        urlPatterns.add("/review");
        urlPatterns.add("/rectab");
        urlPatterns.add("/ifLikeMovie");
        registrationBean.addUrlPatterns(urlPatterns.toArray(new String[urlPatterns.size()]));
        return registrationBean;
    }

}
