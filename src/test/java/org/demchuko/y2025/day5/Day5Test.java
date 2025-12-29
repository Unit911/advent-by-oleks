package org.demchuko.y2025.day5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class Day5Test {

    @InjectMocks
    Day5 day5;


    @Test
    void verifyNormalizeRanges() {
        List<Map.Entry<Double, Double>> ranges = new ArrayList<>();
        ranges.add(new AbstractMap.SimpleEntry<>(3.0, 5.0));


        List<Map.Entry<Double, Double>> result = day5.normalizedRanges(ranges);

        assertThat(result).hasSize(2);
        assertThat(result.get(0)).isEqualTo(new AbstractMap.SimpleEntry<>(3.0, 5.0));
        assertThat(result.get(1)).isEqualTo(new AbstractMap.SimpleEntry<>(10.0, 24.0));
    }

    @ParameterizedTest
    @MethodSource("testDataForFirstTask")
    void verifyTask1(List<Map.Entry<Double, Double>> ranges, List<Double> ids, int expectedResult) {
        int result = day5.solveFirstTask(ranges, ids);
        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("testDataForSecondTask")
    void verifyTask2(List<Map.Entry<Double, Double>> ranges, double expectedResult) {
        double result = day5.solveSecondTask(ranges);
        assertThat(result).isEqualTo(expectedResult);
    }

    public static Stream<Arguments> testDataForFirstTask() {
        return Stream.of(
                Arguments.of(getRanges(), getIds(), 681),
                Arguments.of(getTestRanges(), getTestIds(), 3)
        );
    }


    public static Stream<Arguments> testDataForSecondTask() {
        return Stream.of(
                Arguments.of(getRanges(), 348820208020395.0),
                Arguments.of(getTestRanges(), 14.0)
        );

    }

    static List<Map.Entry<Double, Double>> getTestRanges() {
        List<Map.Entry<Double, Double>> ranges = new ArrayList<>();
        ranges.add(new AbstractMap.SimpleEntry<>(3.0, 5.0));
        ranges.add(new AbstractMap.SimpleEntry<>(10.0, 14.0));
        ranges.add(new AbstractMap.SimpleEntry<>(16.0, 20.0));
        ranges.add(new AbstractMap.SimpleEntry<>(12.0, 18.0));
        return ranges;
    }

    static List<Double> getTestIds() {
        List<Double> ids = new ArrayList<>();
        ids.add(1.0);
        ids.add(5.0);
        ids.add(8.0);
        ids.add(11.0);
        ids.add(17.0);
        ids.add(32.0);
        return ids;
    }

    static List<Map.Entry<Double, Double>> getRanges() {
        List<Map.Entry<Double, Double>> ranges = new ArrayList<>();
        ranges.add(new AbstractMap.SimpleEntry<>(124495185257650.0, 128523382238124.0));
        return ranges;
    }

    static List<Double> getIds() {
        List<Double> ids = new ArrayList<>();
        ids.add(345448950878627.0);
        return ids;
    }

}