package org.siksnaghae.fgmate.util;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

public class ApiUtil {
    private static final RestTemplate restTemplate = new RestTemplateBuilder()
            .setConnectTimeout(Duration.ofSeconds(30))
            .setReadTimeout(Duration.ofSeconds(30))
            .build();

    public static <T> T reqAPI(String url, String requestBody, HttpHeaders headers, Class <T> type, HttpMethod httpMethod) {
        ResponseEntity<T> response = restTemplate.exchange(url,httpMethod, new HttpEntity<>(requestBody, headers), type);

        return response.getBody();
    }

}
