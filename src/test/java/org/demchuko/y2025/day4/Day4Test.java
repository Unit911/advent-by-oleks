package org.demchuko.y2025.day4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class Day4Test {

    @InjectMocks
    Day4 day4;

    @Test
    void verifyTask1() {
        int result = day4.solveTask1(getTestData());
        assertThat(result).isEqualTo(13);
    }

    @Test
    void verifyTask2() {
        int result = day4.solveTask2(getData());
        assertThat(result).isEqualTo(43);
    }

    List<String> getTestData() {
        List<String> testData = new ArrayList<>();
        return testData;
    }

    List<String> getData() {
        List<String> testData = new ArrayList<>();
        return testData;
    }

}