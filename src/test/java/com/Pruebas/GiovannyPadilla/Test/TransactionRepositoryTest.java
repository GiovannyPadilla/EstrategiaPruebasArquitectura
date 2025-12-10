package com.Pruebas.GiovannyPadilla.Test;

import com.Pruebas.GiovannyPadilla.Test.Repository.Transaction;
import com.Pruebas.GiovannyPadilla.Test.Repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TransactionRepositoryTest {

    @Autowired
    TransactionRepository repo;

    @Test
    void testFindByDateRange() {

        repo.save(new Transaction(new BigDecimal("10.00"), LocalDate.of(2024,1,1)));
        repo.save(new Transaction(new BigDecimal("20.00"), LocalDate.of(2024,1,10)));

        var result = repo.findByDateBetween(
                LocalDate.of(2024,1,1),
                LocalDate.of(2024,1,5)
        );

        assertEquals(1, result.size());
    }
}
