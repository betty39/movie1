package recommend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import java.util.*;
import recommend.common.jwt.*;


@SpringBootApplication
@EnableDiscoveryClient
public class RecommendMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecommendMicroserviceApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        //添加需要拦截的url
        List<String>  urlPatterns = new ArrayList();
        urlPatterns.add("/movieLists");
        registrationBean.addUrlPatterns(urlPatterns.toArray(new String[urlPatterns.size()]));
        return registrationBean;
    }

}
