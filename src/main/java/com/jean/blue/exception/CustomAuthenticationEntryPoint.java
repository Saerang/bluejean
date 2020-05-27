package com.jean.blue.exception;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // status를 401 에러로 지정
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // json 리턴 및 한글깨짐 수정.
        response.setContentType("application/json;charset=utf-8");
        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        String message = "잘못된 접근입니다";
        object.addProperty("code", "9999");
        object.addProperty("message", message);
        String json = gson.toJson(object);
    }
}
