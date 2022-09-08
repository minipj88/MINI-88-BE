package com.ujutechnology.api8.filter;

import com.ujutechnology.api8.biz.service.MemberService;
import com.ujutechnology.api8.security.JwtToken;
import com.ujutechnology.api8.security.MemberAuth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kei
 * @since 2022-09-08
 */
@Slf4j
@Component
@Order(1)
@RequiredArgsConstructor
public class TokenFilter implements Filter {
    private final MemberService memberService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("token");
        String bearer = ((HttpServletRequest)request).getHeader(HttpHeaders.AUTHORIZATION);
        if (bearer == null || !bearer.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return ;
        }

        String token = bearer.substring("Bearer ".length());
        String email = JwtToken.Decode(token);
        MemberAuth auth = MemberAuth.builder().email(email).token(token).build();
        try {
            memberService.getToken(auth);
            ((HttpServletRequest)request).getSession().setAttribute("auth", auth);
        } catch (Exception e) {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return ;
        }
        chain.doFilter(request, response);
    }
}
