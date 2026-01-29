package org.demchuko.y2015.day8;

import lombok.extern.slf4j.Slf4j;
import org.demchuko.Utils;

import java.util.List;
import java.util.function.Function;

@Slf4j
public class Task {
    
//    https://adventofcode.com/2015/day/9

    public static void main(String[] args) {
        final List<String> inputData = Utils.getData("y2015/day8/inputdata");

        Task task = new Task();
        int result = task.solveTask1(inputData, task::calcWhiteSpaces);
        log.info("Result is {}", result);

        int result2 = task.solveTask1(inputData, task::calcSomething2);
        log.info("Result is {}", result2);
    }

    int solveTask1(List<String> inputData, Function<String, Integer> f) {
        int sum = inputData.stream()
                .map(f)
                .mapToInt(Integer::intValue)
                .sum();
        return sum;
    }

    int calcWhiteSpaces(String str) {
        char[] charArray = str.toCharArray();
        int whitespaces = 2;
        //skip start and end as it's always quotes
        for (int i = 1; i < charArray.length - 1; i++) {
            if (charArray[i] == '\\') {
                if (charArray[i + 1] == 'x') {
                    i += 3;
                    whitespaces += 3;
                } else {
                    i += 1;
                    whitespaces += 1;
                }
            }
        }
        return whitespaces;
    }

    int calcSomething2(String str) {
        char[] charArray = str.toCharArray();
        int whitespaces = 4; //start and end
        for (int i = 1; i < charArray.length - 1; i++) {
            if (charArray[i] == '\\' || charArray[i] == '"') {
                whitespaces++;
            }
        }
        return whitespaces;
    }


}
