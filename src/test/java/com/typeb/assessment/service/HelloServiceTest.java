package com.typeb.assessment.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HelloServiceTest {

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

    // A–M range

    @ParameterizedTest
    @ValueSource(strings = {"alice", "Alice", "ALICE", "a", "A", "mike", "Mike", "m", "M"})
    void firstHalfAlphabet_isValid(String name) {
        assertThat(service.isValidName(name)).isTrue();
    }

    // N–Z range

    @ParameterizedTest
    @ValueSource(strings = {"nancy", "Nancy", "ZARA", "n", "N", "z", "Z"})
    void secondHalfAlphabet_isInvalid(String name) {
        assertThat(service.isValidName(name)).isFalse();
    }

    // Boundary cases

    @Test
    void letterM_isValidBoundary() {
        assertThat(service.isValidName("mike")).isTrue();
    }

    @Test
    void letterN_isInvalidBoundary() {
        assertThat(service.isValidName("nick")).isFalse();
    }


}
