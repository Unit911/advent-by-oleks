package org.demchuko.y2025.day11;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Day11 {

    public final static String YOU = "you";
    public final static String OUT = "out";
    public final static String SVR = "svr";
    public final static String DAC = "dac";
    public final static String FFT = "fft";

    public int solveTask1(List<String> input) {

        Map<String, Set<String>> map = new HashMap<>();

        input.forEach(line -> {
            String[] split = line.split(":");
            String key = split[0].trim();
            String[] split1 = split[1].trim().split("\\s");
            map.put(key, Arrays.stream(split1).collect(Collectors.toSet()));
        });

        return find(YOU, map);
    }

    public double solveTask22(List<String> input) {
        Map<String, Set<String>> map = new HashMap<>();

        input.forEach(line -> {
            String[] split = line.split(":");
            String key = split[0].trim();
            String[] split1 = split[1].trim().split("\\s");
            map.put(key, Arrays.stream(split1).collect(Collectors.toSet()));
        });

        double svr = find(SVR, FFT, map, new HashMap<>());
        double fft = find(FFT, DAC, map, new HashMap<>());
        double dac = find(DAC, OUT, map, new HashMap<>());
        return svr * fft * dac;
    }

    int find(String s, Map<String, Set<String>> map) {
        Set<String> strings = map.get(s);
        if (strings.contains(OUT)) {
            return 1;
        }
        return strings
                .stream()
                .map(string -> find(string, map))
                .mapToInt(Integer::intValue)
                .sum();
    }

    double find(String s, String end, Map<String, Set<String>> map, Map<String, Double> paths) {
        if (s.equals(OUT) && !end.equals(OUT)) {
            return 0;
        }
        Set<String> strings = map.get(s);

        if (strings.contains(end)) {
            return 1;
        }
        if (paths.containsKey(s)) {
            return paths.get(s);
        }
        double sum = strings
                .stream()
                .map(string -> find(string, end, map, paths))
                .mapToDouble(Double::doubleValue)
                .sum();
        paths.put(s, sum);
        return sum;
    }
}