package org.demchuko.y2025.day5;

import lombok.extern.slf4j.Slf4j;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Day5 {

    public List<Map.Entry<Double, Double>> normalizedRanges(List<Map.Entry<Double, Double>> ranges) {
        return normalizedRanges(ranges, 0);
    }

    public List<Map.Entry<Double, Double>> normalizedRanges(List<Map.Entry<Double, Double>> ranges, int attempt) {
        int difference = 0;
        int originalSize = ranges.size();
        log.info("Provided ranges: {}", ranges.size());
        ranges.sort((o1, o2) -> (o1.getKey() < o2.getKey()) ? -1 : 1);
        for (int i = 0; i < ranges.size() - 1; i++) {
            Map.Entry<Double, Double> range = ranges.get(i);
            Map.Entry<Double, Double> rangeNext = ranges.get(i + 1);
            if (range.getValue() >= rangeNext.getKey() && range.getValue() <= rangeNext.getValue()) {
                Map.Entry<Double, Double> newEntry = new AbstractMap.SimpleEntry<>(range.getKey(), rangeNext.getValue());
                log.info("Merging two entries original: {} next: {}, newEntry: {}", range, rangeNext, newEntry);
                ranges.set(i, newEntry);
                ranges.remove(i + 1);
                i--;
            } else if (range.getValue() > rangeNext.getValue()) {
                log.info("Removing redundant entry original: {} redundant: {}", range, rangeNext);
                ranges.remove(i + 1);
                i--;
            }
        }
        log.info("Normalized ranges atttempt:{} : {}", attempt, ranges.size());
        difference = originalSize - ranges.size();
        if (difference > 0) {
            return normalizedRanges(ranges, ++attempt);
        }
        return ranges;
    }

    public int isFresh(List<Map.Entry<Double, Double>> normalizedRange, Double id) {
        for (int i = 1; i < normalizedRange.size() + 1; i++) {
            if (i == normalizedRange.size() || normalizedRange.get(i).getKey().compareTo(id) > 0) {
                Map.Entry<Double, Double> prevRange = normalizedRange.get(i - 1);
                if (prevRange.getKey().compareTo(id) <= 0 &&
                        prevRange.getValue().compareTo(id) >= 0) {
                    log.info("ID {} belong to range {}", id, prevRange);
                    return 1;
                } else {
                    break;
                }
            }
        }
        log.info("ID {} does not belong to any range", id);
        return 0;
    }

    public int solveFirstTask(List<Map.Entry<Double, Double>> ranges, List<Double> ids) {
        log.info("Solving First Task");
        List<Map.Entry<Double, Double>> normalizedRanges = normalizedRanges(ranges);
        AtomicInteger count = new AtomicInteger();
        ids.stream()
                .forEach(
                        id -> {
                            count.addAndGet(isFresh(normalizedRanges, id));
                        }
                );
        log.info("Fresh ID count: {}", count.get());
        return count.intValue();
    }

    public double solveSecondTask(List<Map.Entry<Double, Double>> ranges) {
        List<Map.Entry<Double, Double>> normalizedRanges = normalizedRanges(ranges);

        double freshCount = 0;
        for (int i = 0; i < ranges.size(); i++) {
            Map.Entry<Double, Double> range = normalizedRanges.get(i);
            freshCount += (range.getValue() - range.getKey() + 1);
        }
        return freshCount;
    }
}
