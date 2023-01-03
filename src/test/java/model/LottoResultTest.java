package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    @ParameterizedTest
    @MethodSource("generateLottosResults")
    @DisplayName("로또 수익률 계산")
    void lottosResults(List<List<Integer>> lottosList, WinningLotto winningLotto, long money, double expectedEarningRate) {
        LottoGame lottoGame = new LottoGame(createRandomNumbers(lottosList));
        lottoGame.generate(lottosList.size());
        LottoResult lottoResult = new LottoResult(lottoGame, winningLotto);
        assertThat(lottoResult.getEarningRate(money)).isEqualTo(expectedEarningRate);
    }

    static Stream<Arguments> generateLottosResults() {
        return Stream.of(
                Arguments.of(
                        List.of(List.of(1, 2, 3, 4, 5, 6), List.of(7, 8, 9, 10, 11, 12)),
                        new WinningLotto("1, 2, 3, 4, 5, 6", 7),
                        2000L,
                        (double) (2_000_000_000L) / 2000L
                ), // 6개 일치
                Arguments.of(
                        List.of(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 6)),
                        new WinningLotto("1, 2, 3, 4, 5, 6", 7),
                        2000L,
                        (double) (2_000_000_000L + 2_000_000_000L) / 2000L
                ), // 6개 일치 + 6개 일치
                Arguments.of(
                        List.of(List.of(5, 4, 3, 2, 1, 9), List.of(3, 5, 6, 7, 2, 4)),
                        new WinningLotto("1, 2, 3, 4, 5, 6", 7),
                        2000L,
                        (double) (1_500_000L + 30_000_000L) / 2000L
                ), // 5개 일치 + 5개 일치, 보너스 일치
                Arguments.of(
                        List.of(List.of(1, 2, 3, 34, 35, 36), List.of(1, 2, 3, 4, 35, 36)),
                        new WinningLotto("1, 2, 3, 4, 5, 6", 7),
                        2000L,
                        (double) (5_000L + 50_000L) / 2000L
                ) // 3개 일치, 4개 일치
        );
    }

    private RandomNumbers createRandomNumbers(List<List<Integer>> returnValue) {
        return new RandomNumbers() {
            int index = 0;

            public List<Integer> generate() {
                return returnValue.get(index++);
            }
        };
    }
}
