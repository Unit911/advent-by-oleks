package org.demchuko;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Day0 {

   public static final String INPUT1 = "INPUT1";
   public static final String INPUT2 = "INPUT2";

    public String foo(String input) {
        log.info("Running foo {}", input);
        String result;
        if (input.equals(INPUT1)) {
            result = "OUTPUT1";
        } else if (input.equals(INPUT2)) {
            result = "OUTPUT2";
        } else {
            result = "OUTPUT_DEFAULT";
        }
        log.info("Returning {}", result);
        return result;
    }


    public static void main(String[] args) {
        Day0 day0 = new Day0();
        day0.foo(INPUT1);
    }

}
