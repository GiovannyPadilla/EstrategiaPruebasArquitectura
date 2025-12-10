package com.Pruebas.GiovannyPadilla.Service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CommissionServiceImpl implements CommissionService {

    @Override
    public BigDecimal calculate(BigDecimal amount) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

        BigDecimal commission;

        if (amount.compareTo(BigDecimal.valueOf(1000)) <= 0) {
            commission = amount.multiply(BigDecimal.valueOf(0.02));
        } else if (amount.compareTo(BigDecimal.valueOf(5000)) <= 0) {
            commission = amount.multiply(BigDecimal.valueOf(0.03));
        } else {
            commission = amount.multiply(BigDecimal.valueOf(0.05));
        }

        return commission.setScale(2, RoundingMode.HALF_UP);
    }
}
