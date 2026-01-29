package org.demchuko.core.hex;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Int16 {

    private final static Map<Integer, Integer> pows = new HashMap<>();
    private final static int size = 16;

    static {
        for (int i = 0; i <= size; i++) {
            pows.put(i, (int) Math.round(Math.pow(2, i)));
        }
    }

    @Getter
    private final boolean[] bits;

    public Int16(boolean[] bits) {
        this.bits = new boolean[size];
        for (int i = 0; i < size; i++) {
            this.bits[i] = bits[i];
        }
    }

    public Int16(int number) {
        if (pows.get(size) - 1 < number) {
            throw new RuntimeException("number is too big to use it here");
        }
        if (number < 0) {
            throw new RuntimeException("number is negative");
        }

        int numberClone = number;
        bits = new boolean[size];
        for (int i = size - 1; i >= 0; i--) {
            if (pows.get(i) <= numberClone) {
                bits[i] = true;
                numberClone -= pows.get(i);
            }
        }
    }

    public Int16(Int16 other) {
        this.bits = new boolean[size];
        for (int i = 0; i < size; i++) {
            this.bits[i] = other.bits[i];
        }
    }

    public Int16 and(Int16 other) {
        boolean[] bits = new boolean[size];

        for (int i = 0; i < size; i++) {
            bits[i] = other.bits[i] && this.bits[i];
        }

        return new Int16(bits);
    }

    public Int16 or(Int16 other) {
        boolean[] bits = new boolean[size];

        for (int i = 0; i < size; i++) {
            bits[i] = other.bits[i] || this.bits[i];
        }

        return new Int16(bits);
    }

    public Int16 not() {


        for (int i = 0; i < size; i++) {
            bits[i] = !this.bits[i];
        }

        return new Int16(bits);
    }

    public Int16 lshift(Int16 other) {
        return lshift(other.toInteger());
    }

    public Int16 lshift(int shiftSize) {
        boolean[] bits = new boolean[size];
        final int normShiftSize = shiftSize % size;

        for (int i = normShiftSize; i < size; i++) {
            bits[i] = this.bits[i - normShiftSize];
        }

//        for (int i = 0; i < normShiftSize; i++) {
//            bits[i] = this.bits[i + (size - normShiftSize)];
//        }
        return new Int16(bits);

    }

    public Int16 rshift(Int16 other) {
        return rshift(other.toInteger());
    }

    public Int16 rshift(int shiftSize) {
        boolean[] bits = new boolean[size];
        final int normShiftSize = shiftSize % size;
        final int sizeMShift = size - normShiftSize;
        for (int i = 0; i < sizeMShift; i++) {
            bits[i] = this.bits[i + normShiftSize];
        }

//        for (int i = sizeMShift; i < size; i++) {
//            bits[i] = this.bits[i - sizeMShift];
//        }
        return new Int16(bits);
    }

    public int toInteger() {
        int number = 0;
        for (int i = 0; i < size; i++) {
            if (bits[i]) {
                number += pows.get(i);
            }
        }
        return number;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = size - 1; i >= 0; i--) {
            sb.append(bits[i] ? '1' : '0');
        }
        return sb.toString();
    }


}
