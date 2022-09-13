package com.chuyashkou.hotels_booking.filter;

import com.chuyashkou.hotels_booking.util.PageManager;
import com.chuyashkou.hotels_booking.util.PageMappingConstant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "SearchHotelsFilter",
        urlPatterns = {"/jsp/searchHotels.jsp", "/jsp/hotelPage.jsp", "/jsp/user/bookingPage.jsp", "/jsp/user/userBookingsPage.jsp"})
public class SearchHotelsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath()
                + PageManager.getPageURI(PageMappingConstant.MAIN_PAGE.getKey()));
        chain.doFilter(request, response);
    }
}
