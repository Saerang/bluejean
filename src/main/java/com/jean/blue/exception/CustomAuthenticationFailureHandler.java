package com.jean.blue.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Getter
@Setter
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private String username;
    private String password;
    private String loginErrorMsg;
    private String defaultFailUrl;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        //로그인 정보 저장
        String userId = request.getParameter("username");
        String password = request.getParameter("password");
        String errorMsg = exception.getMessage();

        request.setAttribute("userId", userId);
        request.setAttribute("password", password);
        request.setAttribute("errorMsg", errorMsg);

        /*
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> data = new HashMap<>();
        data.put("timestamp", Calendar.getInstance().getTime());
        data.put("exception", exception.getMessage());
        response.getOutputStream().println(objectMapper.writeValueAsString(data));
        */

        request.getRequestDispatcher("/login/error").forward(request, response);
    }
}
