package com.talonone.integrationapi.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class ApiKeyAuthFilter extends OncePerRequestFilter {
    private static final String API_KEY_HEADER = "Authorization";
    private static final String API_KEY_PREFIX = "ApiKey-v1 ";
    // For demonstration, use a static API key. In production, load from DB or config.
    private static final String DEMO_API_KEY = "demo-key";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader(API_KEY_HEADER);
        if (StringUtils.hasText(header) && header.startsWith(API_KEY_PREFIX)) {
            String apiKey = header.substring(API_KEY_PREFIX.length());
            if (DEMO_API_KEY.equals(apiKey)) {
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        apiKey, null, Collections.emptyList());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid API Key");
                return;
            }
        } else if (isSwaggerOrH2(request)) {
            filterChain.doFilter(request, response);
            return;
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or invalid Authorization header");
            return;
        }
        filterChain.doFilter(request, response);
    }

    private boolean isSwaggerOrH2(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return uri.startsWith("/swagger-ui") || uri.startsWith("/v3/api-docs") || uri.startsWith("/h2-console");
    }
}
