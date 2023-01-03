package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoTest {
    @ParameterizedTest
    @MethodSource("generateLottoScoreData")
    @DisplayName("지난 주 당첨번호와 로또 번호를 비교해 올바른 결과값 객체를 생성한다.")
    void lottoScoreTest(String lottoString, int bonusNumber, Lotto lotto, LottoScore expected) {
        WinningLotto winningLotto = new WinningLotto(lottoString, bonusNumber);
        LottoScore result = winningLotto.getScore(lotto);
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> generateLottoScoreData() {
        return Stream.of(
                Arguments.of("1, 2, 3, 4, 5, 6", 7, new Lotto(List.of(1, 2, 3, 4, 5, 6)), new LottoScore(6, false)),
                Arguments.of("1, 2, 3, 4, 5, 6", 7, new Lotto(List.of(1, 2, 3, 4, 6, 7)), new LottoScore(5, true)),
                Arguments.of("1, 2, 3, 4, 5, 6", 12, new Lotto(List.of(1, 2, 3, 4, 5, 45)), new LottoScore(5, false)),
                Arguments.of("1, 2, 3, 4, 5, 6", 12, new Lotto(List.of(1, 2, 3, 4, 44, 45)), new LottoScore(4, false)),
                Arguments.of("1, 2, 3, 4, 5, 6", 12, new Lotto(List.of(1, 2, 3, 43, 44, 45)), new LottoScore(3, false)),
                Arguments.of("1, 2, 3, 4, 5, 6", 12, new Lotto(List.of(1, 2, 43, 43, 44, 45)), new LottoScore(2, false)),
                Arguments.of("1, 2, 3, 4, 5, 6", 12, new Lotto(List.of(1, 41, 42, 43, 44, 45)), new LottoScore(1, false)),
                Arguments.of("1, 2, 3, 4, 5, 6", 12, new Lotto(List.of(40, 41, 42, 43, 44, 45)), new LottoScore(0, false))
        );
    }
}
