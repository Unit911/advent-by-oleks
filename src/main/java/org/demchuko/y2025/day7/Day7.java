package org.demchuko.y2025.day7;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class Day7 {

    public static char START_CHAR = 'S';
    public static char SPLITTER = '^';
    public static char BEAM = '|';

    List<char[]> outputMap;

    int solveTask1(List<String> inputData) {

        outputMap = new ArrayList<>();
        for (int i = 0; i < inputData.size(); i++) {
            outputMap.add(inputData.get(i).toCharArray());
        }

        Set<Integer> currentBeamIndexes = new HashSet<>();
        currentBeamIndexes.add(inputData.get(0).indexOf(START_CHAR));

        int splitCount = 0;
        for (int i = 1; i < inputData.size(); i++) {
            Set<Integer> nextBeamIndexes = new HashSet<>();
            for (int beamIndex : currentBeamIndexes) {
                Set<Integer> newBeamIndexes = splitBeam(inputData.get(i), beamIndex);
                if (!newBeamIndexes.isEmpty()) {
                    nextBeamIndexes.addAll(newBeamIndexes);
                    splitCount++;
                } else {
                    nextBeamIndexes.add(beamIndex);
                }
            }
            currentBeamIndexes = nextBeamIndexes;

            for (int beamIndex : currentBeamIndexes) {
                outputMap.get(i)[beamIndex] = BEAM;
            }
        }

        log.info("FINAL MAP: ");
        for (char[] line : outputMap) {
            log.info("{}", new String(line));
        }

        return splitCount;
    }


    Set<Integer> splitBeam(String inputData, int beamLocation) {
        if (inputData.charAt(beamLocation) == SPLITTER) {
            Set<Integer> result = new HashSet<>();
            if (beamLocation > 0) {
                result.add(beamLocation - 1);
            }
            if (beamLocation != inputData.length() - 1) {
                result.add(beamLocation + 1);
            }
            return result;
        }
        return Collections.emptySet();
    }

    void updateTimelines(String inputData, int beamLocation, Map<Integer, Double> timelines) {
        if (inputData.charAt(beamLocation) == SPLITTER) {
            double entries = timelines.get(beamLocation);
            timelines.put(beamLocation - 1, timelines.getOrDefault(beamLocation - 1, 0.0) + entries);
            timelines.put(beamLocation + 1, timelines.getOrDefault(beamLocation + 1, 0.0) + entries);
            timelines.put(beamLocation, 0.0);
        }
    }

    double solveTask2(List<String> inputData) {

        Map<Integer, Double> timelines = new HashMap<>();
        timelines.put(inputData.get(0).indexOf(START_CHAR), 1.0);

        for (int i = 1; i < inputData.size(); i++) {
            Set<Integer> beams = new HashSet<>(timelines.keySet());
            for (int beamIndex : beams) {
                updateTimelines(inputData.get(i), beamIndex, timelines);
            }
        }

        AtomicReference<Double> sum = new AtomicReference<>(0.0);
        timelines.forEach((key, value) -> {
            sum.updateAndGet(v -> v + value);
        });
        return sum.get().doubleValue();
    }

}
