package org.demchuko.y2025.day12;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class Day12 {

    public int solveTask1(List<String> input) {
        int sum = input
                .stream()
                .map(this::isLineOk)
                .mapToInt(Integer::intValue)
                .sum();
        log.info("sum: {}", sum);
        return sum;
    }

    public int isLineOk(String s) {
        final String[] split = s.split(":");
        final String[] boxDimensions = split[0].trim().split("x");

        final int boxWidth = Integer.parseInt(boxDimensions[0].trim());
        final int boxHeight = Integer.parseInt(boxDimensions[1].trim());

        final String[] boxes = split[1].trim().split(" ");

        int sum = Arrays.stream(boxes)
                .mapToInt(Integer::parseInt)
                .sum();

        int boxWide = boxWidth / 3;
        int boxTall = boxHeight / 3;

        int result = boxWide * boxTall >= sum ? 1 : 0;
        return result;
    }

}
