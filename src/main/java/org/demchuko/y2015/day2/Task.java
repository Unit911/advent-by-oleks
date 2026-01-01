package org.demchuko.y2015.day2;

import lombok.extern.slf4j.Slf4j;
import org.demchuko.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class Task {

    public static final String MULT_SIGN = "x";

    public static void main(String[] args) {
        log.info("Solving day2");


        final List<String> data = Utils.getData("y2015/day2/inputData");

        Task task = new Task();
        long task1Result = task.solveTask(data, task::solveTask);
        log.info("Day2 result is {}", task1Result);

        long task2Result = task.solveTask(data, task::solveTask2);
        log.info("Day2 result is {}", task2Result);
    }

    public long solveTask(List<String> inputData, Function<String, Long> function) {
        long sum = inputData.stream()
                .map(function)
                .mapToLong(Long::longValue)
                .sum();
        return sum;
    }

    public long solveTask(String inputData) {
        final List<Long> vals = Arrays.stream(inputData.split(MULT_SIGN))
                .map(Long::parseLong)
                .sorted()
                .toList();
        
        return 3 * vals.get(0) * vals.get(1) + 2 * vals.get(1) * vals.get(2) + 2 * vals.get(0) * vals.get(2);
    }
    
    public long solveTask2(String inputData) {
        final List<Long> vals = Arrays.stream(inputData.split(MULT_SIGN))
                .map(Long::parseLong)
                .sorted()
                .toList();
        return vals.get(0) * vals.get(1) * vals.get(2) +  2 * vals.get(0) + 2 * vals.get(1);         
    }

}
