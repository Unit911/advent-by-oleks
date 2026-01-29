package org.demchuko.core.hex;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class Int16Test {

    public static Stream<Arguments> lshiftData() {
        return Stream.of(
                Arguments.of(123, 2, 492),
                Arguments.of(123, 0, 123),
                Arguments.of(123, 16, 123),
                Arguments.of(123, 32, 123),
                Arguments.of(123, 34, 492),
                Arguments.of(456, 14, 114),
                Arguments.of(0, 23, 0),
                Arguments.of(0, 1, 0),
                Arguments.of(1, 31, 32768)
        );
    }

    public static Stream<Arguments> rshiftData() {
        return Stream.of(
                Arguments.of(456, 2, 114),
                Arguments.of(456, 0, 456),
                Arguments.of(456, 16, 456),
                Arguments.of(456, 32, 456),
                Arguments.of(456, 34, 114),
                Arguments.of(123, 30, 492),
                Arguments.of(1, 31, 2)
        );
    }

    public static Stream<Arguments> andData() {
        return Stream.of(
                Arguments.of(123, 456, 72),
                Arguments.of(1, 1, 1),
                Arguments.of(3, 1, 1),
                Arguments.of(32768, 1, 0),
                Arguments.of(65535, 0, 0)
                
        );
    }

    public static Stream<Arguments> orData() {
        return Stream.of(
                Arguments.of(123, 456, 507),
                Arguments.of(1, 1, 1),
                Arguments.of(3, 1, 3),
                Arguments.of(32768, 1, 32769),
                Arguments.of(65535, 333, 65535),
                Arguments.of(65535, 0, 65535)
        );
    }

    public static Stream<Arguments> notData() {
        return Stream.of(
                Arguments.of(123, 65412),
                Arguments.of(32768, 32767),
                Arguments.of(1, 65534),
                Arguments.of(0, 65535)
                
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 65000, 65535})
    void verifyConstuctorAndReader(int toCheck) {
        Int16 val = new Int16(toCheck);
        assertThat(val.toInteger()).isEqualTo(toCheck);
    }

    @ParameterizedTest
    @ValueSource(ints = {65536, -1})
    void verifyExceptionIsThrown(int toCheck) {
        assertThatThrownBy(() -> new Int16(toCheck))
                .isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest
    @MethodSource("andData")
    void verifyAnd(int num1, int num2, int expected) {
        Int16 x = new Int16(num1);
        Int16 y = new Int16(num2);

        Int16 result = x.and(y);
        assertThat(result.toInteger()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("orData")
    void verifyOr(int num1, int num2, int expected) {
        Int16 x = new Int16(num1);
        Int16 y = new Int16(num2);

        Int16 or = x.or(y);
        assertThat(or.toInteger()).isEqualTo(expected);
    }
    
    @ParameterizedTest
    @MethodSource("notData")
    void verifyNot(int num, int expected) {
        Int16 x = new Int16(num);

        Int16 result = x.not();
        assertThat(result.toInteger()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("lshiftData")
    void verifyLshift(int number, int shiftSize, int expected) {
        Int16 x = new Int16(number);
        log.info("x is {}", x);

        Int16 lshift = x.lshift(shiftSize);
        log.info("lshift is {}", lshift);
        assertThat(lshift.toInteger())
                .as("lshift to integer was: " + x + "  beacme:" + lshift)
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("rshiftData")
    void verifyRshiftAnd(int number, int shiftSize, int expected) {
        Int16 x = new Int16(number);
        Int16 rshift = x.rshift(shiftSize);
        assertThat(rshift.toInteger())
                .as("rshift to integer was: " + x + "  beacme:" + rshift)
                .isEqualTo(expected);
    }

}