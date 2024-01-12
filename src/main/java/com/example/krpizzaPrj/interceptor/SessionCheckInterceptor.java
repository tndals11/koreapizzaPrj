package com.example.krpizzaPrj.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.plugin.Intercepts;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class SessionCheckInterceptor implements HandlerInterceptor { // 구현 Override를 해서 사용해주어야한다
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession hs = request.getSession(); // => session
        // LoginInfo(userid, passwd, nam...) - 객체
        if ( hs == null || hs.getAttribute("user") == null ) { // session을 체크한다
            response.sendRedirect("/common/login?error=session"); // => 잘못된 접속이면 팅겨낸다
            return false;
        }

        return true;
    }
}
