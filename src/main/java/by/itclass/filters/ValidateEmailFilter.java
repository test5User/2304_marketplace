package by.itclass.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

import static by.itclass.constants.AppConst.REGISTRATION_CONTROLLER;
import static by.itclass.constants.JspConst.*;

@WebFilter(REGISTRATION_CONTROLLER)
public class ValidateEmailFilter implements Filter {
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var email = servletRequest.getParameter(EMAIL_PARAM);
        if (!email.matches(EMAIL_REGEX)) {
            servletRequest.setAttribute(MESSAGE_ATTR, "Wrong email!!!");
            servletRequest.getRequestDispatcher(REGISTRATION_JSP).forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
