package com.disney.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;


class CharacterServiceImplTest {

    LocalDate bornDate = LocalDate.of(2000,9,21);
    LocalDate now = LocalDate.now();

    @Test
    void getAge() {
        Period period = Period.between(bornDate, now);
        assertEquals(21, period.getYears());
    }
}