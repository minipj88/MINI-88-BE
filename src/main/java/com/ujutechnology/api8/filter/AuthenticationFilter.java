package com.ujutechnology.api8.filter;

import com.ujutechnology.api8.security.MemberAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author kei
 * @since 2022-09-01
 */
@Slf4j
@WebFilter(urlPatterns = {"/api/member/*","/api/credit/*","/api/cart/*","/api/carts/*", "/api/reservation/*", "/api/reservations/*"})
@Order(2)
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("auth");
        HttpSession session = ((HttpServletRequest)request).getSession();
        MemberAuth auth = (MemberAuth)session.getAttribute("auth");
        if(auth != null){
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_FORBIDDEN);
        }


    }
}
