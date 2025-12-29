package org.demchuko.y2025.day6;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class Day6Test {

    @InjectMocks
    Day6 day6;

    @ParameterizedTest
    @MethodSource("testDataFirstTask")
    void verifyFirstTask(List<String> data, Double expectedResult) {
        double i = day6.solveTask1(data);
        assertThat(i).isEqualTo(expectedResult);
    }
    
    @ParameterizedTest
    @MethodSource("testDataSecondTask")
    void verifySecondTask(List<String> data, Double expectedResult) {
        double i = day6.solveTask2(data);
        assertThat(i).isEqualTo(expectedResult);
    }

    static Stream<Arguments> testDataFirstTask() {
        return Stream.of(
                Arguments.of(getTestData("testData.txt"), 4277556.0),
                Arguments.of(getTestData("data"), 7229350537438.0)
        );
    }
    static Stream<Arguments> testDataSecondTask() {
        return Stream.of(
                Arguments.of(getTestData("testData.txt"), 3263827.0),
                Arguments.of(getTestData("data"), 11479269003550.0)
        );
    }

    @SneakyThrows
    static List<String> getTestData(String filename) {
        return Files.readAllLines(Path.of(Day6Test.class.getClassLoader().getResource("day6/" + filename).toURI()));
    }

    @Test
    void verifyReadSignsForSecondTask() {
        List<String> data = new ArrayList<>();
        data.add("*   +   *   +  ");

        //when
        List<Map.Entry<Character, Integer>> entries = day6.readSignsForSecondTask(data);

        assertThat(entries).hasSize(4);
        assertThat(entries.get(0).getKey()).isEqualTo('*');
        assertThat(entries.get(0).getValue()).isEqualTo(3);
        assertThat(entries.get(1).getKey()).isEqualTo('+');
        assertThat(entries.get(1).getValue()).isEqualTo(3);
        assertThat(entries.get(2).getKey()).isEqualTo('*');
        assertThat(entries.get(2).getValue()).isEqualTo(3);
        assertThat(entries.get(3).getKey()).isEqualTo('+');
        assertThat(entries.get(3).getValue()).isEqualTo(2);
    }

}