package org.demchuko.y2025.day4;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Day4 {

    public static char PAPER_ROLL = '@';
    public static char EMPTY_SPOT = '.';
    public static String EMPTY_SPOT_STRING = ".";

    int solveTask1(List<String> data) {

        int X_SIZE = data.size();
        int Y_SIZE = data.get(0).length();
        int numberOfRolls = 0;

        numberOfRolls = getNumberOfAccessibleRolls(data, X_SIZE, Y_SIZE);

        return numberOfRolls;
    }

    private int getNumberOfAccessibleRolls(List<String> data, int X_SIZE, int Y_SIZE) {
        int numberOfRolls = 0;
        for (int i = 0; i < X_SIZE; i++) {
            for (int j = 0; j < Y_SIZE; j++) {
                if (data.get(i).charAt(j) == EMPTY_SPOT) {
                    continue;
                }
                if (isRollAccessible(data, i, j)) {
                    numberOfRolls++;
                }
            }
        }
        return numberOfRolls;
    }

    int solveTask2(List<String> data) {
        int X_SIZE = data.size();
        int Y_SIZE = data.get(0).length();
        int numberOfRolls = 0;

        int numberOfAccessibleRolls;
        do {
            numberOfAccessibleRolls = solveTask2(data, X_SIZE, Y_SIZE);
            numberOfRolls += numberOfAccessibleRolls;
        } while (numberOfAccessibleRolls != 0);

        return numberOfRolls;
    }

    int solveTask2(List<String> data, int X_SIZE, int Y_SIZE) {
        int numberOfRolls = 0;
        for (int i = 0; i < X_SIZE; i++) {
            for (int j = 0; j < Y_SIZE; j++) {
                if (data.get(i).charAt(j) == EMPTY_SPOT) {
                    continue;
                }
                if (isRollAccessible(data, i, j)) {
                    updateData(data, i, j);
                    numberOfRolls++;
                }
            }
        }
        return numberOfRolls;
    }

    private static void updateData(List<String> data, int i, int j) {
        String s = data.get(i);
        String updateData = s.substring(0, j);
        updateData = updateData.concat(EMPTY_SPOT_STRING);
        if (j + 1 < s.length()) {
            updateData = updateData.concat(s.substring(j + 1));
        }
        data.set(i, updateData);
    }

    boolean isRollAccessible(List<String> data, int x, int y) {
        int amountOfRollsAround = tryToCheckRoll(data, x - 1, y - 1) +
                tryToCheckRoll(data, x - 1, y) +
                tryToCheckRoll(data, x - 1, y + 1) +
                tryToCheckRoll(data, x, y - 1) +
                tryToCheckRoll(data, x, y + 1) +
                tryToCheckRoll(data, x + 1, y - 1) +
                tryToCheckRoll(data, x + 1, y) +
                tryToCheckRoll(data, x + 1, y + 1);
        return amountOfRollsAround < 4;
    }

    int tryToCheckRoll(List<String> data, int x, int y) {
        try {
            if (data.get(x).charAt(y) == PAPER_ROLL) {
                return 1;
            }
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
        return 0;
    }

}
