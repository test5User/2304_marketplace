package by.itclass.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

import static by.itclass.constants.AppConst.LOGIN_CONTROLLER;
import static by.itclass.constants.AppConst.REGISTRATION_CONTROLLER;
import static by.itclass.constants.JspConst.*;

@WebFilter({LOGIN_JSP, REGISTRATION_JSP, LOGIN_CONTROLLER, REGISTRATION_CONTROLLER})
public class RegisteredUserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var session = ((HttpServletRequest) servletRequest).getSession();
        var user = session.getAttribute(USER_ATTR);
        if (Objects.nonNull(user)) {
            servletRequest.getRequestDispatcher(HOME_JSP).forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
