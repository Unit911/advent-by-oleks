package org.demchuko.y2025.day6;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Day6 {

    public static char PLUS = '+';
    public static char MULT = '*';
    public static String spaceRegex = "\\s+";

    Double solveTask1(List<String> data) {
        List<List<Double>> processedData = getParsedNumbersForFirstTask(data);
        List<Character> signs = readSignsForFirstTask(data);
        return doSquidMath(processedData, signs);

    }

    Double solveTask2(List<String> data) {
        List<Character> signs = readSignsForFirstTask(data);

        int signIndex = 0;
        List<Double> results = new ArrayList<>();
        results.add(0.0);
        for (int i = 0; i < data.get(0).length(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < data.size() - 1; j++) {
                sb.append(data.get(j).charAt(i));
            }
            String number = sb.toString().trim();
            if (number.isEmpty()) {
                signIndex++;
                results.add(0.0);
            } else {
                Double v = applyOperation(results.get(results.size() - 1), Double.valueOf(number), signs.get(signIndex));
                results.set(results.size() - 1, v);
            }
        }

        double sum = results
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        log.info("Sum is {}", sum);

        return sum;
    }

    private double doSquidMath(List<List<Double>> processedData, List<Character> signs) {
        Double[] result = processedData.get(0)
                .stream()
                .map(v -> Double.parseDouble(v.toString()))
                .toArray(Double[]::new);

        for (int i = 1; i < processedData.size(); i++) {
            List<Double> numbers = processedData.get(i);
            for (int j = 0; j < processedData.get(0).size(); j++) {
                result[j] = applyOperation(result[j], numbers.get(j), signs.get(j));
            }
        }

        double sum = 0;
        for (int i = 0; i < result.length; i++) {
            sum += result[i];
        }

        return sum;
    }

    private List<Character> readSignsForFirstTask(List<String> data) {
        return Arrays.stream(data.get(data.size() - 1).split(spaceRegex))
                .map(String::toCharArray)
                .map(chars -> chars[0])
                .collect(Collectors.toList());
    }

    public List<Map.Entry<Character, Integer>> readSignsForSecondTask(List<String> data) {
        List<Map.Entry<Character, Integer>> result = new ArrayList<>();
        char[] charArray = data.get(data.size() - 1).toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == ' ') {
                Map.Entry<Character, Integer> characterIntegerEntry = result.get(result.size() - 1);
                characterIntegerEntry.setValue(characterIntegerEntry.getValue() + 1);
            } else {
                result.add(new AbstractMap.SimpleEntry<>(charArray[i], 0));
            }
        }
        return result;
    }

    private List<List<Double>> getParsedNumbersForFirstTask(List<String> data) {
        List<List<Double>> processedData = new ArrayList<>();

        for (int i = 0; i < data.size() - 1; i++) {
            processedData.add(Arrays.stream(data.get(i).trim().split(spaceRegex))
                    .map(String::trim)
                    .map(Double::parseDouble)
                    .collect(Collectors.toList()));
        }
        return processedData;
    }

    private Double applyOperation(Double result, Double integer, Character character) {
        if (PLUS == character) {
            result += integer;
        } else if (MULT == character) {
            if (result.equals(0.0)) {
                result = 1.0;
            }
            result *= integer;
        }
        return result;
    }


}
