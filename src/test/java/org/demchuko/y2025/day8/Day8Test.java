package org.demchuko.y2025.day8;

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
public class Day8Test {

    @InjectMocks
    private Day8 underTest;
    
    @ParameterizedTest
    @MethodSource("foo")
    void solveTask1(List<String> data, int connections, int expectedResult) {
        int result = underTest.solveTask1(data, connections);
        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("foo2")
    void solveTask2(List<String> data, int expectedResult) {
        int result = underTest.solveTask2(data);
        assertThat(result).isEqualTo(expectedResult);
    }


    static Stream<Arguments> foo() {
        return Stream.of(
                Arguments.of(Utils.getData("day8/testData"), 10, 40),
                Arguments.of(Utils.getData("day8/data"), 1000, 40)
        );
    }
    
    static Stream<Arguments> foo2() {
        return Stream.of(
                Arguments.of(Utils.getData("day8/data"), 25272)
        );
    }

}
