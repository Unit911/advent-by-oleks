package org.demchuko.y2025.day12;

import org.demchuko.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class Day12Test {

    @InjectMocks
    private org.demchuko.y2025.day12.Day12 underTest;
    
    @Test
    public void testTask1() {
        List<String> data = Utils.getData("day12/data");
        int i = underTest.solveTask1(data);
        assertThat(i).isEqualTo(1);

    }
    
}