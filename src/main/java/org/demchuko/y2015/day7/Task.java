package org.demchuko.y2015.day7;

import lombok.extern.slf4j.Slf4j;
import org.demchuko.Utils;
import org.demchuko.core.hex.Int16;

import java.util.*;

import static org.demchuko.y2015.day7.Operation.NONE;

@Slf4j
public class Task {

    public static void main(String[] args) {
        Task task = new Task();

        final List<String> inputData = Utils.getData("y2015/day7/inputData");

        final Map<String, Data> task1Map = new HashMap<>();
        final Map<String, Data> task2Map = new HashMap<>();
        inputData.forEach(input -> {
            String[] split = input.split("->");
            final String key = split[1].trim();
            final String expression = split[0];
//            final Data data = task.buildData2(expression);
//            log.info("Data is here: key={}, data={}", key, data);
            task1Map.put(key, task.buildData2(expression));
            task2Map.put(key, task.buildData2(expression));
        });

//        final Map<String, Data> task2Map = new HashMap<>(task1Map);

        int result1 = task.solveTask("a", task1Map);
        log.info("result1={}", result1);

        task2Map.put("b", Data.builder().operation(NONE).val(new Int16(result1)).build());
        int result2 = task.solveTask("a", task2Map);
        log.info("result2={}", result2);


    }


    public int solveTask(String targetKey, Map<String, Data> map) {
        return getSolution(targetKey, map)
                .toInteger();
    }

    public Int16 getSolution(String key, Map<String, Data> keys) {
        if (key.matches("\\d+")) {
            log.info("key={} no need to look for data", key);
            return new Int16(Integer.parseInt(key));
        }
        final Data data = keys.get(key);
        log.info("key={}, data={}", key, data);
        if (data.val != null) {
            return data.val;
        }
        final Operation operation = data.operation;
        final String key1 = data.keys.get(0);
        final Int16 calcValue = switch (operation) {
            case NONE -> getSolution(key1, keys);
            case LSHIFT -> getSolution(key1, keys).lshift(data.shiftVal);
            case RSHIFT -> getSolution(key1, keys).rshift(data.shiftVal);
            case AND -> getSolution(key1, keys).and(getSolution(data.keys.get(1), keys));
            case OR -> getSolution(key1, keys).or(getSolution(data.keys.get(1), keys));
            case NOT -> getSolution(key1, keys).not();
        };
        data.val = calcValue;
        log.info("Calculated!!! key={}, data={}", key, data);
        return calcValue;
    }

    public Data buildData2(String expression) {
        final Operation operation = getOperation(expression);

        final Data.DataBuilder data = Data
                .builder()
                .operation(operation);
        switch (operation) {
            case NONE:
                data.keys(Collections.singletonList(expression.trim()));
                break;
            case LSHIFT:
            case RSHIFT:
                final String[] split = expression.split(operation.text);
                data.keys(Collections.singletonList(split[0].trim()))
                        .shiftVal(Integer.parseInt(split[1].trim()));
                break;
            case NOT:
                final String key = expression.replace(operation.text, "").trim();
                data.keys(Collections.singletonList(key));
                break;
            case AND:
            case OR:
                final String[] split1 = expression.split(operation.text);
                data.keys(Arrays.asList(split1[0].trim(), split1[1].trim()));
                break;
        }

        return data.build();
    }

    private Operation getOperation(String s) {
        return Arrays.stream(Operation.values())
                .filter(operation -> s.contains(operation.text))
                .findAny()
                .orElse(NONE);
    }

}