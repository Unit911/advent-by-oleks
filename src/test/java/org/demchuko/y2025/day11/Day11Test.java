package org.demchuko.y2025.day11;

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

@ExtendWith(MockitoExtension.class)
class Day11Test {

    @InjectMocks
    private Day11 underTest;

    public static Stream<Arguments> task1Data() {
        return Stream.of(
                Arguments.of(Utils.getData("day11/testData"), 5),
                Arguments.of(Utils.getData("day11/data"), 566)
        );
    }

    @ParameterizedTest
    @MethodSource("task1Data")
    void verifyTask1(List<String> input, int expected) {
        int result = underTest.solveTask1(input);
        assertThat(result).isEqualTo(expected);
    }

    public static Stream<Arguments> task2Data() {
        return Stream.of(
                Arguments.of(Utils.getData("day11/testData2"), 2.0)
                ,
                Arguments.of(Utils.getData("day11/data"), 331837854931968.0)
                
        );
    }

    @ParameterizedTest
    @MethodSource("task2Data")
    void verifyTask2(List<String> input, double expected) {
        double result = underTest.solveTask22(input);
        assertThat(result).isEqualTo(expected);
    }

}