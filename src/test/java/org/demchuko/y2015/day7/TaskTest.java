package org.demchuko.y2015.day7;

import org.demchuko.Utils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TaskTest {

    @InjectMocks
    private Task underTest;

    public static Stream<Arguments> testdata() {
        return Stream.of(
                Arguments.of("d", 72),
                Arguments.of("e", 507),
                Arguments.of("f", 492),
                Arguments.of("g", 114),
                Arguments.of("h", 65412),
                Arguments.of("i", 65079),
                Arguments.of("x", 123),
                Arguments.of("y", 456),
                Arguments.of("xx", 123),
                Arguments.of("xy", 492),
                Arguments.of("xz", 492),
                Arguments.of("zx", 72)
        );
    }

    //todo - fix tests
//    @ParameterizedTest
//    @MethodSource("testdata")
//    void testTask1(String targetKey, int expectedReuslt) {
//        List<String> data = Utils.getData("y2015/day7/testData");
//
//        int result = underTest.solveTask(targetKey, data);
//        assertThat(result).isEqualTo(expectedReuslt);
//    }

}