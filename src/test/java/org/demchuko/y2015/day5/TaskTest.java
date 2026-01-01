package org.demchuko.y2015.day5;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TaskTest {

    
    @InjectMocks
    Task task;

    public static Stream<Arguments> task1data() {
        
        return Stream.of(
                Arguments.of("ugknbfddgicrmopn", true),
                Arguments.of("aaa", true),
                Arguments.of("jchzalrnumimnmhp", false),
                Arguments.of("haegwjzuvuyypxyu", false),
                Arguments.of("dvszwmarrgswjxmb", false)
        );
    }


    @ParameterizedTest
    @MethodSource("task1data")
    void verifyTask1(String input, boolean expectedResult) {
        boolean result = task.isNice1(input);
        assertThat(result).isEqualTo(expectedResult);
    }



    public static Stream<Arguments> task2data() {

        return Stream.of(
                Arguments.of("qjhvhtzxzqqjkmpb", true),
                Arguments.of("xxyxx", true),
                Arguments.of("uurcxstgmygtbstg", false),
                Arguments.of("ieodomkazucvgmuy", false)
        );
    }
    
    @ParameterizedTest
    @MethodSource("task2data")
    void verifyTask2(String input, boolean expectedResult) {
        boolean result = task.isNice2(input);
        assertThat(result).isEqualTo(expectedResult);
    }
    
    
    
}