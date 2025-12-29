package org.demchuko.y2025.day2;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class Day2 {

    //https://adventofcode.com/2025/day/2

    //find invalid product ids
    public List<Double> findInvalidsForFirstTask(double start, double end) {


        //find rank
        int rank = findMidRank(start);

        double pow = Math.pow(10, rank);
        double startSimilar = Math.floor(start / pow);

        if (startSimilar < pow / 10) {
            startSimilar = pow / 10;
        }
        List<Double> result = new ArrayList<>();

        boolean inRange = true;
        do {
            if (startSimilar >= pow) {
                pow *= 10;
            }
            double invalidId = startSimilar * pow + startSimilar;
            if (invalidId < start) {
                startSimilar++;
                continue;
            }
            inRange = invalidId <= end;
            if (inRange) {
                result.add(invalidId);
                log.info("invalid {} in range {} - {}", invalidId, start, end);
            }
            startSimilar++;
        } while (inRange);

        return result;
    }

    public int findMidRank(double number) {
        return (int) Math.floor(Math.ceil(Math.log10(number) + 1) / 2);
    }

    public List<Double> findInvalidsForSecondTask(double lowLimit, double end) {
        double start = lowLimit;
        List<Double> result = new ArrayList<>();
        boolean inRange = true;
        do {
            int midRank = findMidRank(start);
            for (int i = 1; i <= midRank; i++) {
                result.addAll(findInvalidsForRank(start, end, i));
            }
            start = Math.pow(10, findMaxRank(start) + 1);
            if (start > end) {
                inRange = false;
            }
        } while (inRange);
        return result;
    }

    public double findMaxRank(double number) {
        return Math.floor(Math.log10(number));
    }

    public List<Double> findInvalidsForRank(double start, double limit, int numberOfDigitsInPattern) {
        double maxRank = findMaxRank(start);
        if (start > limit) {
            return Collections.EMPTY_LIST;
        }

        if ((maxRank + 1) % numberOfDigitsInPattern != 0) {
            return Collections.EMPTY_LIST;
        }

        double repetitions = (maxRank + 1) / numberOfDigitsInPattern;

        if (repetitions < 2) {
            return Collections.EMPTY_LIST;
        }

        double pattern = Math.floor(start / Math.pow(10, maxRank - (numberOfDigitsInPattern - 1)));

        List<Double> result = new ArrayList<>();

        boolean inRange = false;
        do {
            double number = 0;
            if (pattern >= Math.pow(10, numberOfDigitsInPattern)) {
                inRange = false;
                continue;
            }
            for (int i = 0; i < repetitions; i++) {
                number += pattern * Math.pow(10, i * numberOfDigitsInPattern);
            }

            if (number < start) {
                pattern++;
                inRange = true;
                continue;
            }
            if (number <= limit) {
                result.add(number);
                pattern++;
                inRange = true;
            } else {
                inRange = false;
            }
        } while (inRange);
        return result;

    }


    public static void main(String[] args) {
        String input = "197-407,262128-339499,557930-573266,25-57,92856246-93001520,2-12,1919108745-1919268183,48414903-48538379,38342224-38444598,483824-534754,1056-1771,4603696-4688732,75712519-75792205,20124-44038,714164-782292,4429019-4570680,9648251-9913729,6812551522-6812585188,58-134,881574-897488,648613-673853,5261723647-5261785283,60035-128980,9944818-10047126,857821365-857927915,206885-246173,1922-9652,424942-446151,408-1000";
        List<String> ranges = Arrays.asList(input.split(","));

        Day2 d = new Day2();

        Set<Double> invalidIds = new HashSet<>();
        for (String range : ranges) {
            String[] split = range.split("-");
            invalidIds.addAll(d.findInvalidsForSecondTask(Double.parseDouble(split[0]), Double.parseDouble(split[1])));
        }

        double sum = invalidIds.stream().mapToDouble(f -> f.doubleValue()).sum();

        log.info("SUM is: {}", sum);
    }

}
