package org.demchuko.y2015.day6;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demchuko.Utils;

import java.util.Arrays;
import java.util.List;

import static org.demchuko.y2015.day6.Task.Action.TOGGLE;

@Slf4j
public class Task {

    public final static String COMMA = ",";
    public final static String through = "through";

    public static void main(String[] args) {
        Task task = new Task();

        List<String> data = Utils.getData("y2015/day6/inputData");

        int result = task.solveTask1(data);
        log.info("result: {}", result);
    }

    public int solveTask1(List<String> input) {
        boolean[][] states = new boolean[1000][1000];
        for (int i = 0; i < states.length; i++) {
            Arrays.fill(states[i], false);
        }


        input.forEach(s -> process(states, s));


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

    public void process(boolean[][] light, String input) {
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
                light[i][j] = calcNewValue(light[i][j], action);
            }

        }
    }

    Action getAction(String input) {
        return Arrays.stream(Action.values())
                .filter(action -> input.startsWith(action.value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid input"));
    }

    String cutOutAction(String input, Action a) {
        return input.replace(a.value, "");
    }
    
    boolean calcNewValue(boolean currentValue, Action a) {
        return switch (a) {
            case TOGGLE -> !currentValue;
            case TURN_OFF -> false;
            case TURN_ON -> true;
        };
    }

    @AllArgsConstructor
    enum Action {
        TOGGLE("toggle"),
        TURN_OFF("turn off"),
        TURN_ON("turn on");

        final String value;
    }


}
