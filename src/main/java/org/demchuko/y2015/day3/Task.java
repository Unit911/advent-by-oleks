package org.demchuko.y2015.day3;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.demchuko.Utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class Task {

    public final static char UP = '^';
    public final static char DOWN = 'v';
    public final static char LEFT = '<';
    public final static char RIGHT = '>';

    public static void main(String[] args) {

        final String inputData = Utils.getData("y2015/day3/inputData").get(0);
//        final String inputData = "^>v<";
        final Task task = new Task();

        log.info("Solving task1 for day3");
        final long result1 = task.solveTask1(inputData);
        log.info("result {}", result1); 
        
        log.info("Solving task1 for day3");
        final long result2 = task.solveTask2(inputData);
        log.info("result {}", result2);

    }

    public long solveTask1(String path) {
        int x = 0, y = 0;

        final Set<Position> houses = new HashSet<>();
        houses.add(new Position(x, y));
        for (char c : path.toCharArray()) {
            switch (c) {
                case UP:
                    y++;
                    break;
                case DOWN:
                    y--;
                    break;
                case LEFT:
                    x--;
                    break;
                case RIGHT:
                    x++;
                    break;
            }
            houses.add(new Position(x, y));
        }
        return houses.size();
    }

    public long solveTask2(String path) {
        int[] x = new int[2], y = new int[2];
        Arrays.fill(x, 0);

        final Set<Position> houses = new HashSet<>();
        houses.add(new Position(x[0], y[0]));
        final char[] charArray = path.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            switch (c) {
                case UP:
                    y[i % 2]++;
                    break;
                case DOWN:
                    y[i % 2]--;
                    break;
                case LEFT:
                    x[i % 2]--;
                    break;
                case RIGHT:
                    x[i % 2]++;
                    break;
            }
            houses.add(new Position(x[i % 2], y[i % 2]));
        }
        return houses.size();
    }

    @Builder
    @EqualsAndHashCode
    static class Position {
        final int x, y;
    }

}
