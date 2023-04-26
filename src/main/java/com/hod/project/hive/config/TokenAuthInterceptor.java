package com.hod.project.hive.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hod.project.hive.common.factory.ApiResponseFactory;
import com.hod.project.hive.common.sesssion.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@Component
public class TokenAuthInterceptor implements HandlerInterceptor {
    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private ObjectMapper objectMapper;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        boolean isValidAuth = false;

        // 토큰 유효성 체크
        if(token != null && sessionManager.isValidSession(token)) {
            isValidAuth = true;
        }

        // 유효성 체크 실패
        if(!isValidAuth) {
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(ApiResponseFactory.createError("401", "세션이 만료 되었습니다. 다시 로그인 해주세요.")));

            return false;
        }

        return true;
    }
}
