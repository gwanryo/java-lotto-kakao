package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomNumbersTest {
    @Test
    @DisplayName("RandomNumbers를 생성할 때 6개의 숫자를 만들어야한다.")
    void generateNumbersLengthTest() {
        RandomNumbers randomNumbers = new RandomNumbers();
        List<Integer> result = randomNumbers.generate();
        assertThat(result.size()).isEqualTo(6);
    }

    @RepeatedTest(100)
    @DisplayName("RandomNumbers를 생성할 때 1~45의 숫자만 생성한다.")
    void generateNumbersRangeTest() {
        RandomNumbers randomNumbers = new RandomNumbers();
        List<Integer> result = randomNumbers.generate();
        assertThat(result).allMatch(i -> i >= 1 && i <= 45);
    }
}
