package com.example.talonintegrationapi.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.io.IOException;

@Configuration
public class SecurityConfig {
    @Value("${api.security.header}")
    private String apiKeyHeader;
    @Value("${api.security.prefix}")
    private String apiKeyPrefix;
    // For demo, use a static API key. In production, use a secure store.
    private static final String DEMO_API_KEY = "demo-api-key";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests()
                .requestMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .addFilterBefore(new ApiKeyAuthFilter(apiKeyHeader, apiKeyPrefix), UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().disable(); // for H2 console
        return http.build();
    }

    @Component
    public static class ApiKeyAuthFilter extends UsernamePasswordAuthenticationFilter {
        private final String header;
        private final String prefix;
        public ApiKeyAuthFilter(String header, String prefix) {
            this.header = header;
            this.prefix = prefix;
        }
        @Override
        protected boolean shouldNotFilter(HttpServletRequest request) {
            String path = request.getRequestURI();
            return path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs") || path.startsWith("/h2-console");
        }
        @Override
        public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
            String apiKey = request.getHeader(header);
            if (StringUtils.hasText(apiKey) && apiKey.startsWith(prefix)) {
                String key = apiKey.substring(prefix.length()).trim();
                if (DEMO_API_KEY.equals(key)) {
                    Authentication auth = new UsernamePasswordAuthenticationToken("apiuser", null, null);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
            chain.doFilter(request, response);
        }
    }
}
