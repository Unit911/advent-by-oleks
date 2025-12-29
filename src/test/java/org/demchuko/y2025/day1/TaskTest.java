package org.demchuko.y2025.day1;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class TaskTest {

    @InjectMocks
    Task underTest;

    public static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(
                        0, 0, 1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testFoo2(int startPosition, String rotation, int touchedTimes) {

        underTest.rotate2(startPosition, rotation);

        assertThat(underTest.touched0).isEqualTo(touchedTimes);
    }

}



