package com.chuyashkou.hotels_booking.filter;

import com.chuyashkou.hotels_booking.model.User;
import com.chuyashkou.hotels_booking.util.PageManager;
import com.chuyashkou.hotels_booking.util.PageMappingConstant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "PostAuthenticationFilter", urlPatterns = {"/jsp/login.jsp", "/jsp/registrationUser.jsp"})
public class PostAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user != null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()
                    + PageManager.getPageURI(PageMappingConstant.MAIN_PAGE.getKey()));
        }
        chain.doFilter(request, response);
    }
}
