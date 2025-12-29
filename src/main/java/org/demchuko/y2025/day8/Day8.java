package org.demchuko.y2025.day8;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
public class Day8 {

    int solveTask1(List<String> data, int numberOfConnections) {
        List<Coordinate> cords = data.stream()
                .map(Coordinate::new)
                .collect(Collectors.toList());

        Set<Set<Coordinate>> circuits = new HashSet<>();
        cords.forEach(c -> {
            HashSet<Coordinate> coordinates = new HashSet<>();
            coordinates.add(c);
            circuits.add(coordinates);
        });

        Map<Double, Map.Entry<Coordinate, Coordinate>> distances = new HashMap<>();


        for (int i = 0; i < data.size(); i++) {
            for (int j = i + 1; j < data.size(); j++) {
                distances.put(cords.get(i).getDistance(cords.get(j)), new AbstractMap.SimpleEntry<>(cords.get(i), cords.get(j)));
            }
        }

        List<Double> distancesList = new ArrayList<>(distances.keySet());
        Collections.sort(distancesList);

        for (int i = 0; i < numberOfConnections; i++) {
            log.info("Processing connection {} of {} ***********************************", i + 1, numberOfConnections);
            int j = i;
            for (; j < distancesList.size(); j++) {
                Map.Entry<Coordinate, Coordinate> nextMinDistance = distances.get(distancesList.get(j));
//                if (isSameCircuit(circuits, nextMinDistance)) {
//                    continue;
//                }
                log.info("Closest nodes: {}; {}", nextMinDistance.getKey(), nextMinDistance.getValue());
                addTransformerToArray(circuits, nextMinDistance);
                break;
            }

            AtomicInteger junkBoxesCount = new AtomicInteger();
            circuits.forEach(circuit -> junkBoxesCount.addAndGet(circuit.size()));
            if (junkBoxesCount.intValue() != 20) {
                log.error("Something is wrong count is not added up");
            }
        }

        int product = 1;
        List<Integer> collect = circuits.stream()
                .map(c -> c.size())
                .collect(Collectors.toList());
        Collections.sort(collect, Collections.reverseOrder());
        for (int i = 0; i < 3; i++) {
            product *= collect.get(i);
        }

        return product;
    }

    int solveTask2(List<String> data) {
        List<Coordinate> cords = data.stream()
                .map(Coordinate::new)
                .collect(Collectors.toList());

        Set<Set<Coordinate>> circuits = new HashSet<>();
        cords.forEach(c -> {
            HashSet<Coordinate> coordinates = new HashSet<>();
            coordinates.add(c);
            circuits.add(coordinates);
        });

        Map<Double, Map.Entry<Coordinate, Coordinate>> distances = new HashMap<>();


        for (int i = 0; i < data.size(); i++) {
            for (int j = i + 1; j < data.size(); j++) {
                distances.put(cords.get(i).getDistance(cords.get(j)), new AbstractMap.SimpleEntry<>(cords.get(i), cords.get(j)));
            }
        }

        List<Double> distancesList = new ArrayList<>(distances.keySet());
        Collections.sort(distancesList);

        int j = 0;
        Map.Entry<Coordinate, Coordinate> nextMinDistance;
        do {
            nextMinDistance = distances.get(distancesList.get(j));
            log.info("Closest nodes: {}; {}", nextMinDistance.getKey(), nextMinDistance.getValue());
            addTransformerToArray2(circuits, nextMinDistance);
            j++;
        } while (circuits.size() > 1);

        return nextMinDistance.getKey().x * nextMinDistance.getValue().x;
    }

    private void addTransformerToArray(Set<Set<Coordinate>> circuits, Map.Entry<Coordinate, Coordinate> nextMinDistance) {
        try {
            Set<Coordinate> node1 = circuits.stream()
                    .filter(c -> c.contains(nextMinDistance.getKey()))
                    .findFirst()
                    .get();
            Set<Coordinate> node2 = circuits.stream()
                    .filter(c -> c.contains(nextMinDistance.getValue()))
                    .findFirst()
                    .get();
            circuits.remove(node2);
            circuits.remove(node1);
            node1.addAll(node2);
            circuits.add(node1);
            log.info("new circuit is: ");
            circuits.forEach(c -> log.info("Circuit: {}", c));
            log.info("There are total circuits: {}", circuits.size());
        } catch (NoSuchElementException e) {
            log.info("No circuit found");
        }
    }

    private void addTransformerToArray2(Set<Set<Coordinate>> circuits, Map.Entry<Coordinate, Coordinate> nextMinDistance) {
        if (circuits.size() == 2) {
            log.info("ANSWER IS:{}", nextMinDistance.getValue().x * nextMinDistance.getKey().x);
        }
        try {
            Set<Coordinate> node1 = circuits.stream()
                    .filter(c -> c.contains(nextMinDistance.getKey()))
                    .findFirst()
                    .get();
            Set<Coordinate> node2 = circuits.stream()
                    .filter(c -> c.contains(nextMinDistance.getValue()))
                    .findFirst()
                    .get();
            circuits.remove(node2);
            circuits.remove(node1);
            node1.addAll(node2);
            circuits.add(node1);
            log.info("new circuit is: ");
            circuits.forEach(c -> log.info("Circuit: {}", c));
            log.info("There are total circuits: {}", circuits.size());
        } catch (NoSuchElementException e) {
            log.info("No circuit found");
        }
    }


    private boolean isSameCircuit(Set<Set<Coordinate>> circuits, Map.Entry<Coordinate, Coordinate> nextMinDistance) {
        for (Set<Coordinate> circut : circuits) {
            if (circut.contains(nextMinDistance.getKey()) && circut.contains(nextMinDistance.getValue())) {
                return true;
            }
        }
        return false;
    }

    @RequiredArgsConstructor
    public static class Coordinate {
        final int x;
        final int y;
        final int z;

        public Coordinate(String s) {
            String[] split = s.split(",");
            x = Integer.parseInt(split[0]);
            y = Integer.parseInt(split[1]);
            z = Integer.parseInt(split[2]);
        }

        double getDistance(Coordinate c) {
            return Math.sqrt(Math.pow(c.x - this.x, 2) + Math.pow(c.y - this.y, 2) + Math.pow(c.z - this.z, 2));
        }

        @Override
        public String toString() {
            return "x: " + x + ", y: " + y + ", z: " + z;
        }
    }

}
