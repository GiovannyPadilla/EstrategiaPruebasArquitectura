package com.Pruebas.GiovannyPadilla.Test.RepositoryTest;

import com.Pruebas.GiovannyPadilla.Test.Repository.Transaction;
import com.Pruebas.GiovannyPadilla.Test.Repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@DataJpaTest
class TransactionRepositoryPgTest {

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:15")
                    .withDatabaseName("testdb")
                    .withUsername("postgres")
                    .withPassword("pass");

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    TransactionRepository repo;

    @Test
    void testPgRangeQuery() {
        repo.save(new Transaction(new BigDecimal("30"), LocalDate.now()));
        var result = repo.findByDateBetween(LocalDate.now().minusDays(1), LocalDate.now());
        assertEquals(1, result.size());
    }
}
