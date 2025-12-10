package com.Pruebas.GiovannyPadilla.Test.Client;

import lombok.Data;

@Data
public class PaymentResponse {
    private String authorizationId;
    private String status;
}
