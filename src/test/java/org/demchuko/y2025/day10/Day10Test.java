package org.demchuko.y2025.day10;

import org.demchuko.Utils;
import org.junit.jupiter.api.Test;
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
class Day10Test {

    @InjectMocks
    Day10 underTest;


    @ParameterizedTest
    @MethodSource("task1Data")
    void verifySolveTask1(List<String> data, double expectedResult) {
        double result = underTest.solveTask1(data);
        assertThat(result).isEqualTo(expectedResult);
    }


    @ParameterizedTest
    @MethodSource("task2Data")
    void verifySolveTask2(List<String> data, double expectedResult) {
        double result = underTest.solveTask2WithZ3(data);
        assertThat(result).isEqualTo(expectedResult);
    }


    public static Stream<Arguments> task1Data() {
        return Stream.of(
                Arguments.of(Utils.getData("day10/testData"), 7),
                Arguments.of(Utils.getData("day10/data"), 475.0)
        );
    }
    public static Stream<Arguments> task2Data() {
        return Stream.of(
                Arguments.of(Utils.getData("day10/testData"), 33.0),
                Arguments.of(Utils.getData("day10/data"), 18273.0)
        );
    }

    @Test
    void verifySolveTask1ForString() {
        double v = underTest.solveTask1ForString("[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}");
        assertThat(v).isEqualTo(3.0);
    }

    @Test
    void verifySolveTask2ForStringWithBruteForce() {
        
        double v = underTest.solveTask2ForStringWithBruteForce("[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}");
        assertThat(v).isEqualTo(10.0);
    }
    
    @Test
    void verifyTask2ForString() {
        double v = underTest.task2ForStringZ3("[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}");
        assertThat(v).isEqualTo(10.0);
    }


}