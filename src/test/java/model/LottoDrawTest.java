package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoDrawTest {
    @ParameterizedTest
    @MethodSource("generateLottoRankData")
    @DisplayName("지난 주 당첨번호와 로또 번호를 비교해 올바른 결과값 객체를 생성한다.")
    void lottoRankTest(String lottoString, int bonusNumber, Lotto lotto, int expected) {
        LottoDraw lottoDraw = new LottoDraw(lottoString, bonusNumber);
        int result = lottoDraw.getRank(lotto);
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> generateLottoRankData() {
        return Stream.of(
                Arguments.of("1, 2, 3, 4, 5, 6", 7, new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7),
                Arguments.of("1, 2, 3, 4, 5, 6", 7, new Lotto(List.of(1, 2, 3, 4, 6, 7)), 6),
                Arguments.of("1, 2, 3, 4, 5, 6", 12, new Lotto(List.of(1, 2, 3, 4, 5, 45)), 5),
                Arguments.of("1, 2, 3, 4, 5, 6", 12, new Lotto(List.of(1, 2, 3, 4, 44, 45)), 4),
                Arguments.of("1, 2, 3, 4, 5, 6", 12, new Lotto(List.of(1, 2, 3, 43, 44, 45)), 3),
                Arguments.of("1, 2, 3, 4, 5, 6", 12, new Lotto(List.of(1, 2, 42, 43, 44, 45)), 2),
                Arguments.of("1, 2, 3, 4, 5, 6", 12, new Lotto(List.of(1, 41, 42, 43, 44, 45)), 1),
                Arguments.of("1, 2, 3, 4, 5, 6", 12, new Lotto(List.of(40, 41, 42, 43, 44, 45)), 0)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5, 6, 7", "1, 2, 3, 4, 5", "1"})
    @DisplayName("6개 이상의 로또 당첨 번호를 입력하면 예외를 발생")
    void invalidLengthOfLottoStringTest(String lottoString) {
        assertThatThrownBy(() -> new LottoDraw(lottoString, 1)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("null 혹은 빈 값 입력 시 예외를 발생")
    void invalidLottoStringTest(String lottoString) {
        assertThatThrownBy(() -> new LottoDraw(lottoString, 1)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    @DisplayName("범위 밖의 보너스 번호를 입력하면 예외를 발생")
    void invalidLengthOfLottoStringTest(int bonusNumber) {
        assertThatThrownBy(() -> new LottoDraw("1, 2, 3, 4, 5, 6", bonusNumber)).isInstanceOf(IllegalArgumentException.class);
    }
}
