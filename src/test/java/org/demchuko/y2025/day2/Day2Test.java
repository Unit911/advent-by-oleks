package org.demchuko.y2025.day2;


import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class Day2Test {

    @InjectMocks
    Day2 day2;

    @ParameterizedTest
    @MethodSource("testDataForInvalidIds")
    public void verifyFindInvalids(double start, double end, List<Double> expected) {
        List<Double> result = day2.findInvalidsForFirstTask(start, end);

        assertThat(result).containsExactly(expected.toArray(new Double[0]));

    }

    static Stream<Arguments> testDataForInvalidIds() {
        return Stream.of(
                Arguments.of(95L, 115L, Arrays.asList(99.0))
        );
    }


    @ParameterizedTest
    @CsvSource({"95,1", "998, 2", "100000, 3", "999000, 3"})
    void verifyFindMinOrder(double number, double expectedOrder) {
        double result = day2.findMidRank(number);
        assertThat(result).isEqualTo(expectedOrder);
    }


    @ParameterizedTest
    @CsvSource({"95,1.0", "998, 2.0", "100000, 5.0", "999000, 5.0"})
    void verifyFindMaxRank(double number, double expectedMaxRank) {
        double result = day2.findMaxRank(number);
        assertThat(result).isEqualTo(expectedMaxRank);
    }


    @ParameterizedTest
    @MethodSource("testDataForFindInvalidsForRank")
    void verifyFindInvalidsForRank(double start, double limit, int numberOfDigitsInPattern, List<Double> expectedResult) {
        List<Double> result = day2.findInvalidsForRank(start, limit, numberOfDigitsInPattern);
        assertThat(result).containsExactly(expectedResult.toArray(new Double[0]));
    }

    public static Stream<Arguments> testDataForFindInvalidsForRank() {
        return Stream.of(
                Arguments.of(11, 22, 1, Arrays.asList(11.0, 22.0))
        );
    }


    @ParameterizedTest
    @MethodSource("testDataForFindInvalidsForSecondTask")
    void verifyFindInvalidsForSecondTask(double start, double end, List<Double> expectedResult) {
        List<Double> result = day2.findInvalidsForSecondTask(start, end);
        assertThat(result).containsExactly(expectedResult.toArray(new Double[0]));
    }

    public static Stream<Arguments> testDataForFindInvalidsForSecondTask() {
        return Stream.of(
                Arguments.of(11, 22, Arrays.asList(11.0, 22.0))
        );
    }
}