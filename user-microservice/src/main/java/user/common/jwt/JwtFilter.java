package user.common.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtFilter extends GenericFilterBean {

    @Autowired
    private Audience audience;

    /**
     *  Reserved claims（保留），它的含义就像是编程语言的保留字一样，属于JWT标准里面规定的一些claim。JWT标准里面定好的claim有：

     iss(Issuser)：代表这个JWT的签发主体；
     sub(Subject)：代表这个JWT的主体，即它的所有人；
     aud(Audience)：代表这个JWT的接收对象；
     exp(Expiration time)：是一个时间戳，代表这个JWT的过期时间；
     nbf(Not Before)：是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的；
     iat(Issued at)：是一个时间戳，代表这个JWT的签发时间；
     jti(JWT ID)：是JWT的唯一标识。
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        //等到请求头信息authorization信息
        final String accessToken = request.getHeader("authorization");
        if (null == accessToken) {
            response.setStatus(401);
            // throw new CommonException(401, "无token，请重新登录");
            chain.doFilter(req, res); // 通过filter进入controller
        }
        request.setAttribute("userid", accessToken);
        Map<String,Object> claims = new HashMap<String,Object>();
        claims.put("userid", accessToken);
        request.setAttribute("claims", claims);
        chain.doFilter(req, res); // 通过filter进入controller
    }
}