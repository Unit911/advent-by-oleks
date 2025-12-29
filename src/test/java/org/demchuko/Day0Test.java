package org.demchuko;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day0Test {

    Day0 day0 = new Day0();

    @ParameterizedTest
    @CsvSource({"INPUT1, OUTPUT1", "INPUT2, OUTPUT2", "SOMETHING_ELSE, OUTPUT_DEFAULT"})
    void verifyFoo(String input, String expected) {
        String result = day0.foo(input);
        assertEquals(result, expected);
    }


}