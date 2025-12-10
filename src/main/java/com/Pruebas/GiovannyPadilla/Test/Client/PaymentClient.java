package com.Pruebas.GiovannyPadilla.Test.Client;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

public class PaymentClient {

    private final String baseUrl;
    private final RestTemplate rest = new RestTemplate();

    public PaymentClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public PaymentResponse authorize(int amount, String currency, String source) {

        Map<String, Object> payload = Map.of(
                "amount", amount,
                "currency", currency,
                "source", source
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        ResponseEntity<PaymentResponse> response = rest.exchange(
                baseUrl + "/payments/authorize",
                HttpMethod.POST,
                request,
                PaymentResponse.class
        );

        return response.getBody();
    }
}
