package org.demchuko.y2025.day10;

import com.microsoft.z3.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Z3Experiment {

  /*  public static void main(String[] args) {

        final String javaLibPathKey = "java.library.path";
//        final String newJavaLibPathVal = "E:\\Dev\\P\\z3\\z3-4.15.4-x64-win\\bin;" + System.getProperty(javaLibPathKey);
//        System.setProperty(
//                javaLibPathKey, newJavaLibPathVal);
//
        log.info("java lib path is {}", System.getProperty(javaLibPathKey));

        int[] joltageReq = {3, 5, 4, 7};
        Day10.Button[] allButtons = new Day10.Button[6];
        allButtons[0] = new Day10.Button("3");
        allButtons[1] = new Day10.Button("1,3");
        allButtons[2] = new Day10.Button("2");
        allButtons[3] = new Day10.Button("2,3");
        allButtons[4] = new Day10.Button("0,2");
        allButtons[5] = new Day10.Button("0,1");

        IntExpr[] buttonPressed = new IntExpr[6];
//        int[] buttonPressed = new int[6];
//        Arrays.fill(buttonPressed, 0);


        Context context = new Context();
        
        context.const
        Solver solver = context.mkSolver();
        
        
        solver.add(context.mkBool(buttonPressed[4] + buttonPressed[5] == joltageReq[0]));
        solver.add(context.mkBool(buttonPressed[1] + buttonPressed[5] == joltageReq[1]));
        solver.add(context.mkBool(buttonPressed[2] + buttonPressed[3] + buttonPressed[4] == joltageReq[2]));
        solver.add(context.mkBool(buttonPressed[3] + buttonPressed[1] == joltageReq[3]));

        Model model = solver.getModel();

    }*/

    public static void main(String[] args) {

        try (Context ctx = new Context()) {

//            "[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}"
//                     0    1   2   3       4   5   
            // Use Optimize for minimization
            Optimize opt = ctx.mkOptimize();

            // Create int variables buttonPressed[0..5]
            IntExpr[] buttonPressed = new IntExpr[6];
            for (int i = 0; i < 6; i++) {
                buttonPressed[i] = ctx.mkIntConst("buttonPressed_" + i);
                opt.Add(ctx.mkGe(buttonPressed[i], ctx.mkInt(0))); // non-negative
            }

            // Constraints
            opt.Add(ctx.mkEq(
                    ctx.mkAdd(buttonPressed[4], buttonPressed[5]),
                    ctx.mkInt(3)));

            opt.Add(ctx.mkEq(
                    ctx.mkAdd(buttonPressed[1], buttonPressed[5]),
                    ctx.mkInt(5)));

            opt.Add(ctx.mkEq(
                    ctx.mkAdd(buttonPressed[2], buttonPressed[3], buttonPressed[4]),
                    ctx.mkInt(4)));

            opt.Add(ctx.mkEq(
                    ctx.mkAdd(buttonPressed[0], buttonPressed[1], buttonPressed[3]),
                    ctx.mkInt(7)));

            // Minimize sum(buttonPressed[i])
            ArithExpr sum = ctx.mkAdd(buttonPressed);
            opt.MkMinimize(sum);

            // Solve
            if (opt.Check() == Status.SATISFIABLE) {
                Model model = opt.getModel();

                int total = 0;
                for (int i = 0; i < 6; i++) {
                    int value = Integer.parseInt(model.evaluate(buttonPressed[i], false).toString());
                    total += value;
                    System.out.println("buttonPressed[" + i + "] = " + value);
                }
                System.out.println("Total sum = " + total);
            } else {
                System.out.println("No solution found.");
            }
        }
    }

}
