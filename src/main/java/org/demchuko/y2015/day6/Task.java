package org.demchuko.y2015.day6;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demchuko.Utils;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class Task {

    public final static String COMMA = ",";
    public final static String through = "through";

    public static void main(String[] args) {
        Task task = new Task();

        List<String> data = Utils.getData("y2015/day6/inputData");

        int result = task.solveTask1(data);
        log.info("result: {}", result);
        
        int result2 = task.solveTask2(data);
        log.info("result2: {}", result2);
    }

    public int solveTask1(List<String> input) {
        boolean[][] states = new boolean[1000][1000];
        for (int i = 0; i < states.length; i++) {
            Arrays.fill(states[i], false);
        }


        input.forEach(s -> processTask1(states, s));


        return countLights(states);
    }

    public int solveTask2(List<String> input) {
        int[][] states = new int[1000][1000];
        for (int i = 0; i < states.length; i++) {
            Arrays.fill(states[i], 0);
        }


        input.forEach(s -> processTask1(states, s));


        return countLights(states);
    }

    public int countLights(boolean[][] states) {
        int count = 0;
        for (int i = 0; i < states.length; i++) {
            for (int j = 0; j < states[0].length; j++) {
                if (states[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public int countLights(int[][] states) {
        int sum = 0;
        for (int i = 0; i < states.length; i++) {
            for (int j = 0; j < states[0].length; j++) {
                sum += states[i][j];
            }
        }
        return sum;
    }

    public void processTask1(boolean[][] light, String input) {
        final Action action = getAction(input);
        input = cutOutAction(input, action);

        final String[] split = input.split(through);

        final String[] start = split[0].trim().split(COMMA);
        int xStart = Integer.parseInt(start[0]);
        int yStart = Integer.parseInt(start[1]);

        final String[] end = split[1].trim().split(COMMA);
        int xEnd = Integer.parseInt(end[0].trim());
        int yEnd = Integer.parseInt(end[1].trim());


        log.info("xStart: {}, yStart: {}, xEnd {}, yEnd {}", xStart, yStart, xEnd, yEnd);
        for (int i = xStart; i <= xEnd; i++) {
            for (int j = yStart; j <= yEnd; j++) {
                light[i][j] = calcNewValue1(light[i][j], action);
            }

        }
    }

    public void processTask1(int[][] light, String input) {
        final Action action = getAction(input);
        input = cutOutAction(input, action);

        final String[] split = input.split(through);

        final String[] start = split[0].trim().split(COMMA);
        int xStart = Integer.parseInt(start[0]);
        int yStart = Integer.parseInt(start[1]);

        final String[] end = split[1].trim().split(COMMA);
        int xEnd = Integer.parseInt(end[0].trim());
        int yEnd = Integer.parseInt(end[1].trim());


        log.info("xStart: {}, yStart: {}, xEnd {}, yEnd {}", xStart, yStart, xEnd, yEnd);
        for (int i = xStart; i <= xEnd; i++) {
            for (int j = yStart; j <= yEnd; j++) {
                light[i][j] = calcNewValue2(light[i][j], action);
            }

        }
    }

    private Action getAction(String input) {
        return Arrays.stream(Action.values())
                .filter(action -> input.startsWith(action.value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid input"));
    }

    private String cutOutAction(String input, Action a) {
        return input.replace(a.value, "");
    }

    boolean calcNewValue1(boolean currentValue, Action a) {
        return switch (a) {
            case TOGGLE -> !currentValue;
            case TURN_OFF -> false;
            case TURN_ON -> true;
        };
    }

    int calcNewValue2(int currentValue, Action a) {
        currentValue += a.incremenet;
        if (currentValue < 0) {
            currentValue = 0;
        }
        return currentValue;
    }

    @AllArgsConstructor
    enum Action {
        TOGGLE("toggle", 2),
        TURN_OFF("turn off", -1),
        TURN_ON("turn on", 1);

        final String value;
        final int incremenet;
    }


}
