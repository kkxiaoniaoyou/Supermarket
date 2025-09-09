package com.cbz.example.interceptor;

import cn.hutool.core.util.StrUtil;
import com.cbz.example.common.Result;
import com.cbz.example.entity.User;
import com.cbz.example.exception.BusinessException;
import com.cbz.example.exception.enums.BusinessExceptionEnum;
import com.cbz.example.exception.enums.SystemExceptionEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT Token 拦截器
 */
@Component
@RequiredArgsConstructor
public class JwtTokenInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        // 从请求头中获取 Token
        String token = request.getHeader("Authorization");

        if(StrUtil.isEmpty(token)) {
            throw new BusinessException(BusinessExceptionEnum.TOKEN_HEADER_NEED.getCode(), BusinessExceptionEnum.TOKEN_HEADER_NEED.getMessage());
        }

        if (jwtTokenProvider.isTokenValid(token)) {
            // Token 验证通过，将用户名放入请求中
            String userJson = jwtTokenProvider.getUserFromToken(token);
            User user = new ObjectMapper().readValue(userJson, User.class);
            request.setAttribute("user", user);
            return true;
        } else {
            // 将 Result 对象转换成 JSON 字符串
            ObjectMapper objectMapper = new ObjectMapper();
            String resultJson = objectMapper.writeValueAsString(Result.error(SystemExceptionEnum.UNAUTHORIZED.getCode(),
                    SystemExceptionEnum.UNAUTHORIZED.getMessage()));

            // 设置响应状态为 401 Unauthorized，并返回 JSON 数据
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(resultJson);
            return false;
        }
    }
}

