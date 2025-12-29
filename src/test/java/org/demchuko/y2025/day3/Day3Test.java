package org.demchuko.y2025.day3;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class Day3Test {

    @InjectMocks
    Day3 day3;

    public static Stream<Arguments> testData() {
        
        return Stream.of(
                Arguments.of("s", 1)
        );
    }

    @ParameterizedTest
   @MethodSource("testData")
    void verifyGetMaxJoltagex(String s, int expectedResult) {
        double result = day3.getMaxJoltage(s, 2);
        assertThat(result).isEqualTo(expectedResult);

    }

    @ParameterizedTest
    @MethodSource("testData")
    void verifyGetMaxJoltagex2(String s, double expectedResult) {
        double result = day3.getMaxJoltage(s, 12);
        assertThat(result).isEqualTo(expectedResult);

    }

}