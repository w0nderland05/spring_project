package org.study.commons.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;

/**
 * 공통 인터셉터
 *
 */
@Component
public class CommonInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String bodyClass = getBodyClass(request);
        request.setAttribute("bodyClass", bodyClass);
        return true;
    }

    private String getBodyClass(HttpServletRequest request) {
        String URI = request.getRequestURI();
        String bodyClass = "body-main";
        if (URI.indexOf(".") == -1) {
            String[] classes = Arrays.stream(URI.split("/")).filter(s -> !s.isBlank() && s.indexOf(".") == -1).toArray(String[]::new);

            if (classes.length > 0) {
                bodyClass = "body-" + classes[0];
                if (classes.length > 1) bodyClass += " body-" + classes[0] + "-" + classes[1];
            }

            return bodyClass;
        }

        return bodyClass;
    }

}
