package org.demchuko.y2015.day5;

import lombok.extern.slf4j.Slf4j;
import org.demchuko.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
public class Task {

    String[] ugly = new String[]{"ab", "cd", "pq", "xy"};
    char[] chars = {'a', 'e', 'i', 'o', 'u'};

    public static void main(String[] args) {

        Task task = new Task();
        List<String> data = Utils.getData("y2015/day5/inputData");

        long result1 = task.countNice1(data);
        log.info("Task1 result {} ", result1);

        long result2 = task.countNice2(data);
        log.info("Task2 result {}", result2);

    }

    public long countNice1(List<String> inputData) {
        return count(inputData, this::isNice1);
    }
    
    public long countNice2(List<String> inputData) {
        return count(inputData, this::isNice2);
    }
    
    
    public long count(List<String> inputData, Function<String, Boolean> filter) {
        long count = inputData
                .stream()
                .map(filter)
                .filter(Boolean::booleanValue)
                .count();
        return count;
    }

    public boolean isNice1(String s) {
        for (int i = 0; i < ugly.length; i++) {
            if (s.contains(ugly[i])) {
                return false;
            }
        }

        return containsXVowels(s, 3) &&
                hasDoubledLetter(s);
    }

    boolean hasDoubledLetter(String s) {
        char prevLetter = '0';
        for (char c : s.toCharArray()) {
            if (c == prevLetter) {
                return true;
            }
            prevLetter = c;
        }
        return false;
    }

    boolean containsXVowels(final String s, final int reqCount) {
        char[] charArray = s.toCharArray();
        int vowlCount = 1;
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (iVowel(c)) {
                vowlCount++;
                if (vowlCount > reqCount) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean iVowel(char c) {
        for (char v : chars) {
            if (c == v) {
                return true;
            }
        }
        return false;
    }

    public boolean isNice2(String s) {
        return containsNonOverlappingPair(s) && repeatWithLetterInBw(s);
    }

    boolean containsNonOverlappingPair(String s) {
        Map<String, Integer> pairWithStartIndex = new HashMap<>();

        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length - 1; i++) {
            String key = new String(new char[]{charArray[i], charArray[i + 1]});

            Integer prevIndex = pairWithStartIndex.get(key);
            if (prevIndex != null && (prevIndex + 1) < i) {
                return true;
            }
            pairWithStartIndex.putIfAbsent(key, i);
        }

        return false;
    }

    boolean repeatWithLetterInBw(String s) {
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length - 2; i++) {
            char a = charArray[i];
            char b = charArray[i + 2];

            if (a == b) {
                return true;
            }
        }
        return false;
    }

}
