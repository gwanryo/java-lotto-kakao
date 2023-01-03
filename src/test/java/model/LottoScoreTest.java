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

    @ParameterizedTest
    @MethodSource("generateCompareData")
    @DisplayName("동일한 로또 점수일 경우 비교 결과가 true가 됨")
    void compareTest(LottoScore lottoScore, LottoScore that, boolean expected) {
        assertThat(lottoScore.compare(that)).isEqualTo(expected);
    }

    static Stream<Arguments> generateCompareData() {
        return Stream.of(
                Arguments.of(
                        new LottoScore(3, false),
                        new LottoScore(3, false),
                        true
                ),
                Arguments.of(
                        new LottoScore(3, false),
                        new LottoScore(3, true),
                        true
                ),
                Arguments.of(
                        new LottoScore(4, false),
                        new LottoScore(4, true),
                        true
                ),
                Arguments.of(
                        new LottoScore(5, false),
                        new LottoScore(5, true),
                        false
                ),
                Arguments.of(
                        new LottoScore(6, false),
                        new LottoScore(6, false),
                        true
                )
        );
    }
}
