package org.demchuko.y2025.day10;

import com.microsoft.z3.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class Day10 {
    //todo - modify so it's not a brute force?

    public static final char ON = '#';

    public double solveTask1(List<String> data) {
        double sum = data
                .stream()
                .map(this::solveTask1ForString)
                .mapToDouble(Double::doubleValue)
                .sum();

        return sum;
    }

    public double solveTask2(List<String> data) {
        double sum = data
                .stream()
                .map(this::solveTask2ForStringWithBruteForce)
                .mapToDouble(Double::doubleValue)
                .sum();

        return sum;
    }

    public double solveTask1ForString(String s) {
        char[] charArray = s.substring(1, s.indexOf(']')).toCharArray();
        boolean targetState[] = new boolean[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            targetState[i] = (charArray[i] == ON);
        }


        List<Button> buttons = new ArrayList<>();
        int start, end = 0;
        while ((start = s.indexOf('(', end)) != -1) {
            end = s.indexOf(')', start);
            String substring = s.substring(start + 1, end);
            buttons.add(new Button(substring));
        }

        Queue<ButtonsPressed> keyCombinations = new LinkedList<>();

        //init
        for (int i = 0; i < buttons.size(); i++) {
            ButtonsPressed e = new ButtonsPressed(buttons.get(i));
            if (e.isWorking(targetState)) {
                return e.buttons.size();
            }
            keyCombinations.add(e);
        }

        while (true) {
            ButtonsPressed poll = keyCombinations.poll();
            for (int i = 0; i < buttons.size(); i++) {
                ButtonsPressed modSequence = new ButtonsPressed(poll);
                modSequence.buttons.add(buttons.get(i));
                if (modSequence.isWorking(targetState)) {
                    log.info("Solved for line: {}", s);
                    return modSequence.buttons.size();
                }
                keyCombinations.add(modSequence);
            }
        }
//        return -1.0;
    }

    public double solveTask2ForStringWithBruteForce(String s) {
        int[] joltageReq = extractJoltageReq(s);
        List<Button> buttons = new ArrayList<>();
        int start, end = 0, position = 0;
        while ((start = s.indexOf('(', end)) != -1) {
            end = s.indexOf(')', start);
            String substring = s.substring(start + 1, end);
            buttons.add(new Button(substring, position++));
        }

        Queue<ButtonsPressed> keyCombinations = new LinkedList<>();

        //init
        for (int i = 0; i < buttons.size(); i++) {
            ButtonsPressed e = new ButtonsPressed(buttons.get(i));
            if (e.isWorking(joltageReq)) {
                log.info("Solved for line: {}", s);
                return e.buttons.size();
            }
            keyCombinations.add(e);
        }

        while (true) {
            ButtonsPressed poll = keyCombinations.poll();
            for (int i = 0; i < buttons.size(); i++) {
                ButtonsPressed modSequence = new ButtonsPressed(poll);
                modSequence.buttons.add(buttons.get(i));
                if (modSequence.isWorking(joltageReq)) {
                    log.info("Solved for line: {}", s);
                    return modSequence.buttons.size();
                }
                if (modSequence.isValid) {
                    keyCombinations.add(modSequence);
                }
            }
        }
    }

    public double solveTask2WithZ3(List<String> data) {
        double sum = data
                .stream()
                .map(this::task2ForStringZ3)
                .mapToDouble(Double::doubleValue)
                .sum();

        return sum;
    }

    /*
     *  Well, I read on reddit that proper solution would be to use Z3 theorem solver from microsoft, and it kind of make sense
     * from buttons and bulb they are affecting one could build a system equation and just feed to solver and get result;
     *  but oh well
     * I guess I went with a challenge to solve it myself;
     *
     * idea is
     * to build greedy algorithm using recursion that will iterate over every possible case;
     *
     * once it's seeing that there are no buttons anymore for the bulbs affected - it should set a maximum (and only
     * available) value for this button.
     * if its not the case just increment by 1 and keep going;
     *
     * If at any point any counter shows bigger value that required - break the loop
     * and incremenet whatever possible;
     * */
    public double task2ForString(String s) {
        final int[] joltageReq = extractJoltageReq(s);
        final int maxJoltage = findMaxJoltage(joltageReq);
        final List<Button> allButtons = extractAllButtons(s);
        final Map<Integer, List<Button>> buttonsByBulb = getButtonsByBulb(allButtons);
        List<Integer> results = new ArrayList<>();

        int[] buttonPressed = new int[allButtons.size()];
        //todo - complete this
//        do {
//            incrementButtonPress(buttonPressed, results);
//        } while ();


        System.out.println();
        return -1;
    }

    //todo - z3
    public double task2ForStringZ3(String s) {
        final int[] joltageReq = extractJoltageReq(s);
        Button[] buttons = extractAllButtons(s).toArray(new Button[0]);
        return solveTask2WithZ3(buttons, joltageReq);
    }

    private void incrementButtonPress(int[] buttonPressed, int buttonToCheck, List<Button> allButtons, Map<Integer, List<Button>> buttonsByBulb) {
//        buttonPressed[buttonToCheck] = calcButtonIncrement();
    }

    public int calcButtonIncrement(int buttonToCheck, int[] buttonPressed, List<Button> allButtons, int[] joltageReq, Map<Integer, List<Button>> buttonsByBulb) {
        Button currentButton = allButtons.get(buttonToCheck);
        int currentButtonIndex = currentButton.index;
        for (int i = 0; i < currentButton.bulbs.size(); i++) {
            Integer bulbIndex = currentButton.bulbs.get(i);
            //if there are no button for this bulb with index higher - calc final increment

            List<Button> buttonsForBulb = buttonsByBulb.get(bulbIndex);
            Optional<Button> buttonForSameBulb = buttonsForBulb
                    .stream()
                    .filter(b -> b.index > currentButtonIndex)
                    .findAny();

            //calculate 
            if (!buttonForSameBulb.isPresent()) {

                for (int j = 0; j < buttonsForBulb.size(); j++) {
                    Button buttonForBulb = buttonsForBulb.get(j);
                    int buttonForBulbIndex = buttonForBulb.index;
                    int currentCount = 0;
                    int targetCount = joltageReq[bulbIndex];
                    if (buttonForBulbIndex != buttonToCheck) {
                        currentCount += buttonPressed[buttonForBulbIndex];
                    }
                }


            }
        }

        //TODO - cont from here i guess
//        if ()
        for (int i = buttonToCheck; i < allButtons.size(); i++) {

        }
        return 1;
    }

    private int[] extractJoltageReq(String s) {
        String[] split = s.substring(s.indexOf('{') + 1, s.indexOf('}')).split(",");
        int[] joltageReq = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            joltageReq[i] = Integer.parseInt(split[i]);
        }
        return joltageReq;
    }

    private List<Button> extractAllButtons(String s) {
        List<Button> allButtons = new ArrayList<>();
        int start, end = 0, position = 0;
        while ((start = s.indexOf('(', end)) != -1) {
            end = s.indexOf(')', start);
            String substring = s.substring(start + 1, end);
            allButtons.add(new Button(substring, position++));
        }
        return allButtons;
    }

    private int findMaxJoltage(int[] joltageReq) {
        int maxReq = -1;
        for (int i = 0; i < joltageReq.length; i++) {
            if (joltageReq[i] > maxReq) {
                maxReq = joltageReq[i];
            }
        }
        return maxReq;
    }

    public Map<Integer, List<Button>> getButtonsByBulb(Button[] allButtons) {
        Map<Integer, List<Button>> buttonsByBulb = new HashMap<>();
        for (int i = 0; i < allButtons.length; i++) {
            Button button = allButtons[i];
            for (Integer bulb : button.bulbs) {
                buttonsByBulb.compute(bulb, (k, v) -> v == null ? new ArrayList<>() : v).add(button);
            }
        }
        return buttonsByBulb;
    }

    public Map<Integer, List<Button>> getButtonsByBulb(List<Button> allButtons) {
        return getButtonsByBulb(allButtons.toArray(new Button[allButtons.size()]));
    }

    /**
     *
     * @param buttonPressed
     * @param joltageReq
     * @return 0 right on spot
     * -1 to low
     * 1 to big
     */
    int isCombinationValid(int[] buttonPressed, List<Button> buttons, int[] joltageReq) {
        int[] joltage = new int[joltageReq.length];
        for (int i = 0; i < joltage.length; i++) {
            joltage[i] = 0;
        }


        for (int i = 0; i < buttonPressed.length; i++) {
            int count = buttonPressed[i];
            Button b = buttons.get(i);
            for (Integer bulb : b.bulbs) {
                joltage[bulb] += count;
                if (joltage[bulb] > joltageReq[bulb]) {
                    return 1;
                }
            }
        }

        for (int i = 0; i < joltageReq.length; i++) {
            if (joltage[i] < joltageReq[i]) {
                return -1;
            }
        }

        return 0;
    }

    //    solveWithZ3
    public int solveTask2WithZ3(Button[] buttons, int[] reqJoltage) {
        try (Context ctx = new Context()) {

            // Use Optimize for minimization
            Optimize opt = ctx.mkOptimize();

            // Create int variables buttonPressed[0..5]
            IntExpr[] buttonPressed = new IntExpr[buttons.length];
            for (int i = 0; i < buttons.length; i++) {
                buttonPressed[i] = ctx.mkIntConst("buttonPressed_" + i);
                opt.Add(ctx.mkGe(buttonPressed[i], ctx.mkInt(0))); // non-negative
            }


            Map<Integer, List<Button>> buttonsByBulb = getButtonsByBulb(buttons);


            for (int i = 0; i < reqJoltage.length; i++) {
                IntExpr[] array = buttonsByBulb.get(i)
                        .stream()
                        .map(b -> b.index)
                        .map(index -> buttonPressed[index])
                        .toArray(IntExpr[]::new);

                opt.Add(ctx.mkEq(ctx.mkAdd(array), ctx.mkInt(reqJoltage[i])));
            }

            // Minimize sum(buttonPressed[i])
            ArithExpr sum = ctx.mkAdd(buttonPressed);
            opt.MkMinimize(sum);

            // Solve
            if (opt.Check() == Status.SATISFIABLE) {
                Model model = opt.getModel();

                int total = 0;
                for (int i = 0; i < buttons.length; i++) {
                    int value = Integer.parseInt(model.evaluate(buttonPressed[i], false).toString());
                    total += value;
                }
                return total;
            } else {
                System.out.println("No solution found.");
            }
        }
        throw new IllegalStateException("No solution found.");
    }

    @RequiredArgsConstructor
    static class ButtonsPressed {
        List<Button> buttons;
        boolean isValid = true;

        public ButtonsPressed(Button b) {
            buttons = new ArrayList<>();
            buttons.add(b);
        }

        public ButtonsPressed(ButtonsPressed b) {
            buttons = new ArrayList<>(b.buttons);
        }

        public boolean isWorking(boolean[] expected) {

            boolean[] calculated = new boolean[expected.length];

            for (int i = 0; i < buttons.size(); i++) {
                for (Integer bulb : buttons.get(i).bulbs) {
                    calculated[bulb] = !calculated[bulb];
                }
            }

            for (int i = 0; i < expected.length; i++) {
                if (expected[i] != calculated[i]) {
                    return false;
                }
            }
            return true;
        }

        public boolean isWorking(int[] joltageReq) {

            int[] calculated = new int[joltageReq.length];

            for (int i = 0; i < buttons.size(); i++) {
                for (Integer bulb : buttons.get(i).bulbs) {
                    calculated[bulb]++;
                    if (calculated[bulb] > joltageReq[bulb]) {
                        isValid = false;
                        return false;
                    }
                }
            }

            for (int i = 0; i < joltageReq.length; i++) {
                if (joltageReq[i] != calculated[i]) {
                    return false;
                }
            }

            return true;


        }

    }


    static class Button {
        String label;
        int index;
        List<Integer> bulbs;

        public Button(String s) {
            new Button(s, -1);
        }

        public Button(String s, int index) {
            label = s;
            this.index = index;
            String[] split = s.split(",");
            bulbs = new ArrayList<>();
            for (int i = 0; i < split.length; i++) {
                bulbs.add(Integer.parseInt(split[i]));
            }
            log.debug("Button created ({})", label);
        }

        @Override
        public String toString() {
            return label;
        }

    }


}
