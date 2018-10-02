package review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import review.common.jwt.JwtFilter;

@SpringBootApplication
@EnableDiscoveryClient
public class ReviewMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReviewMicroserviceApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        //添加需要拦截的url
        List<String> urlPatterns = new ArrayList();
        urlPatterns.add("/lists");
        urlPatterns.add("/create");
        registrationBean.addUrlPatterns(urlPatterns.toArray(new String[urlPatterns.size()]));
        return registrationBean;
    }
}
