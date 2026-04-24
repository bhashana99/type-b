package com.typeb.assessment.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HelloServiceTest {

    private final HelloService service = new HelloService();

    // invalid inputs
    @Test
    void nullName_isInvalid() {
        assertThat(service.isValidName(null)).isFalse();
    }

    @Test
    void emptyName_isInvalid() {
        assertThat(service.isValidName("")).isFalse();
    }

    @Test
    void blankName_isInvalid() {
        assertThat(service.isValidName("   ")).isFalse();
    }

    @Test
    void nameStartingWithDigit_isInvalid() {
        assertThat(service.isValidName("123abc")).isFalse();
    }

}
