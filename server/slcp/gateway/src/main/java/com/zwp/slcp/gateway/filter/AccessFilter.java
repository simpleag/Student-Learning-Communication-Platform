package com.zwp.slcp.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.zwp.slcp.apicommon.constant.MyConstant;
import com.zwp.slcp.apicommon.redis.RedisUtils;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import com.zwp.slcp.apicommon.response.ResponseCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by ASUS on 2017/7/29.
 * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
 pre：可以在请求被路由之前调用
 routing：在路由请求时候被调用
 post：在routing和error过滤器之后被调用
 error：处理请求时发生错误时被调用
 filterOrder：通过int值来定义过滤器的执行顺序
 shouldFilter：返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。在上例中，我们直接返回true，所以该过滤器总是生效。
 run：过滤器的具体逻辑。需要注意，这里我们通过ctx.setSendZuulResponse(false)令zuul过滤该请求，不对其进行路由，然后通过ctx.setResponseStatusCode(401)设置了其返回的错误码，当然我们也可以进一步优化我们的返回，比如，通过ctx.setResponseBody(body)对返回body内容进行编辑等。
 */
public class AccessFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(AccessFilter.class);


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        String frontResult = FrontApiResponseEntity.ERR(ResponseCode.TOKEN_ERROR).build();
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        System.out.println("request" + request.toString());
        logger.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        if (request.getRequestURI().contains("/user/login") || request.getRequestURI().contains("/user/register")) {

        } else {
            try {
                String accessToken = request.getHeader(MyConstant.TOKEN);
                logger.info("header" + accessToken);
                if (accessToken == null || accessToken.isEmpty()) {
                    Cookie[] cookies = request.getCookies();
                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            if (MyConstant.TOKEN.equals(cookie.getName())) {
                                accessToken = cookie.getValue();
                                break;
                            }
                        }
                    }
                    logger.info("cookie" + accessToken);
                }
                if (accessToken == null || accessToken.isEmpty()) {
                    System.out.println(accessToken);
                    logger.error(frontResult);
                    context.setSendZuulResponse(false);
                    context.setResponseStatusCode(401);
//                    context.setRes
                    try {
                        context.getResponse().getWriter().write("token is empty");
                    } catch (Exception e) {
                    }
                    return null;
                } else {
                    String userId = request.getParameter("userId");
                    logger.info("userId"+ userId);
                    String accessTokenEntityString = RedisUtils.getString(userId);
                    System.out.println("token: "+ accessTokenEntityString);
                    JSONObject jsonObject = JSON.parseObject(accessTokenEntityString);
                    String secret = jsonObject.getString("secret");
                    logger.info(secret);
                    String tokenFromRedis = jsonObject.getString("token");
                    logger.info(tokenFromRedis);
                    if (secret==null || userId==null) {
                        context.setSendZuulResponse(false);
                        context.setResponseStatusCode(401);
                        try {
                            context.getResponse().getWriter().write("token is error");
                        } catch (Exception e) {
                        }
                        return null;
                    }
//                    Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(accessToken);
//                    Long tokenUserId = Long.valueOf(claimsJws.getBody().get("accountId").toString());
                    if (accessToken.equals(tokenFromRedis)) {

                        return null;
                    } else {
                        context.setSendZuulResponse(false);
                        context.setResponseStatusCode(401);
                        try {
                            context.getResponse().getWriter().write("token is error");
                        } catch (Exception e) {
                        }
                    }

                }
            } catch (Exception e) {
                if (logger.isErrorEnabled()) {
                    frontResult = FrontApiResponseEntity.ERR(401, ExceptionUtils.getStackTrace(e)).build();
                    logger.error(frontResult);
                }
                // header中令牌不对, 可能被篡改
                logger.error(frontResult);
                //过滤该请求，不往下级服务去转发请求，到此结束
                context.setSendZuulResponse(false);
                context.setResponseStatusCode(401);
                try {
                    context.getResponse().getWriter().write("token is error");
                } catch (Exception ex) {
                }
                return null;
            }
        }
        logger.debug("token is ok");
        return null;
    }

}
