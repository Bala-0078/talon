package com.talon.integrationapi.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;

@Configuration
public class SecurityConfig {
    @Value("${security.api-key-header}")
    private String apiKeyHeader;
    @Value("${security.api-key-prefix}")
    private String apiKeyPrefix;
    @Value("${security.api-key-value}")
    private String apiKeyValue;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests()
                .requestMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .addFilterBefore(new ApiKeyAuthFilter(apiKeyHeader, apiKeyPrefix, apiKeyValue), UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().disable(); // For H2 console
        return http.build();
    }

    static class ApiKeyAuthFilter extends AbstractAuthenticationProcessingFilter {
        private final String header;
        private final String prefix;
        private final String value;
        public ApiKeyAuthFilter(String header, String prefix, String value) {
            super("/**");
            this.header = header;
            this.prefix = prefix;
            this.value = value;
            setAuthenticationManager(new NoOpAuthenticationManager());
        }
        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
            String apiKey = request.getHeader(header);
            if (apiKey != null && apiKey.startsWith(prefix)) {
                String actualKey = apiKey.substring(prefix.length()).trim();
                if (actualKey.equals(value)) {
                    return new UsernamePasswordAuthenticationToken("api-user", null, Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
                }
            }
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid API Key");
            return null;
        }
        @Override
        protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
            super.successfulAuthentication(request, response, chain, authResult);
            chain.doFilter(request, response);
        }
    }
    static class NoOpAuthenticationManager implements AuthenticationManager {
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            return authentication;
        }
    }
}
