package org.demchuko.y2015.day6;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TaskTest {

    @InjectMocks
    private Task testTask;

    public static Stream<Arguments> task1Data() {
        return Stream.of(
                Arguments.of(Arrays.asList("turn on 0,0 through 999,999"), 1000 * 1000),
                Arguments.of(Arrays.asList("turn off 0,0 through 999,999"), 0),
                Arguments.of(Arrays.asList("turn on 0,0 through 999,999",
                        "toggle 0,0 through 999,0"), 999 * 1000),
                Arguments.of(Arrays.asList("turn on 0,0 through 999,999",
                        "toggle 0,0 through 999,0",
                        "turn off 499,499 through 500,500"), 999 * 1000 - 4),
                Arguments.of(Arrays.asList("turn on 0,0 through 999,999",
                        "toggle 0,0 through 999,0",
                        "turn off 499,499 through 500,500",
                        "turn on 499,499 through 500,500"), 999 * 1000),
                Arguments.of(Arrays.asList("toggle 0,0 through 999,999",
                        "toggle 0,0 through 999,999"), 0)

        );
    }


    @ParameterizedTest
    @MethodSource("task1Data")
    void verifySolveTask1(List<String> input, int expectedResult) {
        int result = testTask.solveTask1(input);
        assertThat(result).isEqualTo(expectedResult);
    }

}