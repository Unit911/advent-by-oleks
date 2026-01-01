package org.demchuko.y2015.day1;

import lombok.extern.slf4j.Slf4j;
import org.demchuko.Utils;

@Slf4j
public class Task {

    public final static char DOWN = ')';
    public final static char UP = '(';

    public static void main(String[] args) {
        Task task = new Task();

        int result1 = task.solveTask1(Utils.getData("y2015/day1/inputData").get(0));
        log.info("Result for task1 is {}", result1);

        int result2 = task.solveTask2(Utils.getData("y2015/day1/inputData").get(0));
        log.info("Result2 for task2 is {}", result2);
    }

    public int solveTask1(String input) {

        int up = 0, down = 0;
        for (char c : input.toCharArray()) {
            switch (c) {
                case UP -> up++;
                case DOWN -> down++;
            }
        }

        return up - down;
    }

    public int solveTask2(String input) {
        int lvl = 0;
        char[] charArray = input.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (c == UP) {
                lvl++;
            } else if (c == DOWN) {
                lvl--;
            }
            if (lvl < 0) {
                return i + 1;
            }
        }
        return -1;
    }

}
