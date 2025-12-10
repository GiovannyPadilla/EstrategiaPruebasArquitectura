package com.Pruebas.GiovannyPadilla.ServiceTest;

import com.Pruebas.GiovannyPadilla.Service.CommissionServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommissionServiceImplTest {

    CommissionServiceImpl service = new CommissionServiceImpl();

    @Test
    void negativeAmountReturnsZero() {
        assertEquals("0.00", service.calculate(new BigDecimal("-10")).toString());
    }

    @Test
    void tier1_limit() {
        assertEquals("20.00", service.calculate(new BigDecimal("1000")).toString());
    }

    @Test
    void tier2_limit() {
        assertEquals("150.00", service.calculate(new BigDecimal("5000")).toString());
    }

    @Test
    void tier3_aboveLimit() {
        assertEquals("250.05", service.calculate(new BigDecimal("5001")).toString());
    }

    @Test
    void rounding_half_up() {
        assertEquals("0.02", service.calculate(new BigDecimal("1.234")).toString());
    }
}
