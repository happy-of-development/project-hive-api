package com.hod.project.hive.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hod.project.hive.common.exception.ApiCode;
import com.hod.project.hive.common.exception.ApiException;
import com.hod.project.hive.common.sesssion.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenAuthInterceptor implements HandlerInterceptor {
    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private ObjectMapper objectMapper;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("x-auth-token");
        boolean isValidAuth = false;

        // 토큰 유효성 체크
        if (token != null && sessionManager.isValidSession(token)) {
            isValidAuth = true;
        }

        // 유효성 체크 실패
        if (!isValidAuth) {
            throw new ApiException(ApiCode.UNAUTHORIZED);
        }

        return true;
    }
}
