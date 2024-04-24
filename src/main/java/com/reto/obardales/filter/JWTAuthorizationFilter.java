package com.reto.obardales.filter;

import com.reto.obardales.commons.JWTHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    private final JWTHelper jwtHelper;

    public JWTAuthorizationFilter(JWTHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException {

        try {
            String token = getToken(request);
            if (token != null && this.jwtHelper.validateToken(token)) {
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                this.jwtHelper.getUsernameFromToken(token),
                                null,
                                new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            chain.doFilter(request, response);
        } catch (Exception ex) {
            this.logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }


    private String getToken(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(HEADER);
        if (authenticationHeader != null && authenticationHeader.startsWith(PREFIX)) {
            return authenticationHeader.replace(PREFIX, "");
        }
        return null;
    }

}
