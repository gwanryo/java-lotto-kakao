package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoScoreTest {
    @ParameterizedTest
    @MethodSource("generateWrongScoreData")
    @DisplayName("잘못된 로또 점수 생성시 예외를 발생")
    void wrongScoreTest(int matchNumber, boolean isMatchBonus) {
        assertThatThrownBy(() -> new LottoScore(matchNumber, isMatchBonus)).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> generateWrongScoreData() {
        return Stream.of(
                Arguments.of(-1, true),
                Arguments.of(6, true),
                Arguments.of(7, false),
                Arguments.of(7, true)
        );
    }
}
