package org.myfun.tms_server.security;

import org.apache.commons.lang3.StringUtils;
import org.myfun.tms_server.common.exception.CaptchaException;
import org.myfun.tms_server.common.lang.Const;
import org.myfun.tms_server.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String url = httpServletRequest.getRequestURI();
        if ("/login".equals(url) && httpServletRequest.getMethod().equals("POST")){
            try{
                //校验验证码
                validate(httpServletRequest);
            } catch (CaptchaException e){
//                交给认证失败处理器
                loginFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }

    //校验验证码逻辑
    private void validate(HttpServletRequest  httpServletRequest){
        String code = httpServletRequest.getParameter("code");
        String key = httpServletRequest.getParameter("key");

        if (StringUtils.isBlank(code) || StringUtils.isBlank(key)){
            throw new CaptchaException("验证码错误");
        }
        if (!code.equals(redisUtil.hget(Const.CAPTCHA_KEY,key))){
            throw new CaptchaException("验证码错误");
        }
        //去掉redis中验证码（一次性使用）
        redisUtil.hdel(Const.CAPTCHA_KEY,key);
    }
}

