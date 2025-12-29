package org.demchuko.y2025.day7;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.demchuko.Utils.getData;

@ExtendWith(MockitoExtension.class)
public class Day7Test {

    @InjectMocks
    private Day7 day7;

    public static Stream<Arguments> getDataForFirstTask() {
        return Stream.of(
                Arguments.of(getData("day7/testData"), 21),
                Arguments.of(getData("day7/data"), 1600)
        );
    }

    public static Stream<Arguments> getDataForSecondTask() {
        return Stream.of(
                Arguments.of(getData("day7/data"), 8632253783011.0)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForFirstTask")
    void verifyFirstTask(List<String> inputData, int expectedResult) {
        int result = day7.solveTask1(inputData);
        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("getDataForSecondTask")
    void verifySecondTask(List<String> inputData, double expectedResult) {
        double result = day7.solveTask2(inputData);
        assertThat(result).isEqualTo(expectedResult);
    }

}
