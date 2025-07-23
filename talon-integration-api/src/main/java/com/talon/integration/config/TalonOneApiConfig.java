package com.talon.integration.config;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TalonOneApiConfig {
    @Autowired
    private TalonOneProperties talonOneProperties;

    @Bean
    public RestTemplate talonRestTemplate() {
        HttpClient client = HttpClients.custom().build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "ApiKey-v1 " + talonOneProperties.getApiKey());
            return execution.execute(request, body);
        });
        return restTemplate;
    }
}
