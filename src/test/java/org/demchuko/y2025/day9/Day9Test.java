package org.demchuko.y2025.day9;

import org.demchuko.Utils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({MockitoExtension.class})
class Day9Test {

    @InjectMocks
    Day9 underTest;

    public static Stream<Arguments> getTask1Data() {
        return Stream.of(
                Arguments.of(Utils.getData("day9/testData"), 50.0),
                Arguments.of(Utils.getData("day9/data"), 4737096935.0)
        );
    }

    @ParameterizedTest
    @MethodSource("getTask1Data")
    void verifySolveTask1(List<String> foo, double expectedResult) {
        double i = underTest.solveTask1(foo);
        assertThat(i).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("getTask2Data")
    void verifySolveTask2(List<String> foo, double expectedResult) {
        double i = underTest.solveTask2(foo);
        assertThat(i).isEqualTo(expectedResult);
    }



    public static Stream<Arguments> getTask2Data() {
        return Stream.of(
                Arguments.of(Utils.getData("day9/testData"), 24.0),
                Arguments.of(Utils.getData("day9/data"), 1644094530.0)
        );
    }

}