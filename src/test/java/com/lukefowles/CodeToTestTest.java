package com.lukefowles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CodeToTestTest {
    private CodeToTest test;
    @BeforeEach
    void setUp() {
        CodeToTest test = new CodeToTest();
    }

    @Test
    void canCheckCorrectNumberOfBrackets() {
        //Given
        String input = "({})";
        //When
        boolean actual = test.doBracketsMatch(input);

        //Then
        assertThat(actual).isTrue();

    }
    @Test
    void canCheckIncorrectNumberOfBrackets() {
        //Given
        String input = "({)";

        //When
        boolean actual = test.doBracketsMatch(input);

        //Then
        assertThat(actual).isFalse();

    }
    @Test
    void itShouldCheckWhenInputIsEmpty() {
        //Given
        String input = "";

        //When
        boolean actual = test.doBracketsMatch(input);

        //Then
        assertThat(actual).isFalse();

    }
}