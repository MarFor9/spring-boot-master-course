package com.example.demo.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    private CustomerService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CustomerService(customerRepository);
    }

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    void getCustomers() {
        Customer marcin = new Customer(
                1L,
                "Marcin",
                "hello",
                "marcin.formela@o2.pl");
        Customer ali = new Customer(
                2L,
                "ali",
                "hello",
                "ali@o2.pl");
        customerRepository.saveAll(Arrays.asList(marcin, ali));

        List<Customer> customers = underTest.getCustomers();

        assertEquals(2, customers.size());
    }

    @Test
    void getCustomer() {
        //given
        Customer marcin = new Customer(
                1L,
                "Marcin",
                "hello",
                "marcin.formela@o2.pl");

        customerRepository.save(marcin);

        //when
        Customer actual = underTest.getCustomer(1L);

        //then
        assertEquals(1L, actual.getId());
        assertEquals("Marcin", actual.getName());
        assertEquals("hello", actual.getPassword());
        assertEquals("marcin.formela@o2.pl", actual.getEmail());
    }
}