package com.Pruebas.GiovannyPadilla.Test.contract;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;

import com.Pruebas.GiovannyPadilla.Test.Client.PaymentClient;
import com.Pruebas.GiovannyPadilla.Test.Client.PaymentResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "PaymentProvider")
public class PaymentAuthorizePactTest {

    @Pact(consumer = "PaymentClient")
    public RequestResponsePact createPact(PactDslWithProvider builder) {

        return builder
                .given("authorization is possible")
                .uponReceiving("POST payment authorization")
                .path("/payments/authorize")
                .method("POST")
                .headers(Map.of("Content-Type", "application/json"))
                .body("{\"amount\":100,\"currency\":\"USD\",\"source\":\"CARD_1234\"}")
                .willRespondWith()
                .status(200)
                .headers(Map.of("Content-Type", "application/json"))
                .body("{\"authorizationId\":\"AUTH-9999\",\"status\":\"APPROVED\"}")
                .toPact();
    }

    @Test
    void testPaymentAuthorization(MockServer mockServer) {

        PaymentClient client = new PaymentClient(mockServer.getUrl());

        PaymentResponse response = client.authorize(
                100,
                "USD",
                "CARD_1234"
        );

        assertNotNull(response);
        assertEquals("APPROVED", response.getStatus());
        assertEquals("AUTH-9999", response.getAuthorizationId());
    }
}
