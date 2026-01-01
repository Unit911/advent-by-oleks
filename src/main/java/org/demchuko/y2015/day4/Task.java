package org.demchuko.y2015.day4;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.demchuko.Utils;
import org.slf4j.simple.SimpleLogger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import static org.demchuko.core.hex.Hex.encodeHexString;

@Slf4j
public class Task {

    public final static String ZEROS5 = "00000";
    public final static String ZEROS6 = "000000";

    @SneakyThrows
    public static void main(String[] args) {
        final String input = Utils.getData("y2015/day4/inputData").get(0);
        
        Task task = new Task();
        int task1Result = task.solveTask1(input);
        log.info("task1Result = {}", task1Result);     
        
        
        int task2Result = task.solveTask2(input);
        log.info("task2Result = {}", task2Result);
        
    }

    public int solveTask1(String inputData) {
        return solveTask(inputData, ZEROS5);
    }
    
    public int solveTask2(String inputData) {
        return solveTask(inputData, ZEROS6);
    }

    @SneakyThrows
    public int solveTask(String inputData, String prefix) {
        int key = 0;
        do {
            log.debug("task1, considering {}", key);
            final String code = inputData.concat(String.valueOf(key));
            final byte[] bytesOfMessage = code.getBytes(StandardCharsets.UTF_8);
            final MessageDigest md = MessageDigest.getInstance("MD5");
            final byte[] theMD5digest = md.digest(bytesOfMessage);
            if (encodeHexString(theMD5digest, 3).startsWith(prefix)) {
                return key;
            }
            key++;
        } while (true);
    }


}
    