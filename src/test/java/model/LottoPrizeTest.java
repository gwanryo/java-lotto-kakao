package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoPrizeTest {
    @ParameterizedTest
    @MethodSource("generatePrizeData")
    @DisplayName("로또 점수에 따른 올바른 로또 상금 반환")
    void prizeTest(LottoScore lottoScore, Long expectedPrize) {
        LottoPrize lottoPrize = new LottoPrize();
        Long prize = lottoPrize.getPrize(lottoScore);
        assertThat(prize).isEqualTo(expectedPrize);
    }

    static Stream<Arguments> generatePrizeData() {
        return Stream.of(
                Arguments.of(new LottoScore(0, false), 0L),
                Arguments.of(new LottoScore(1, false), 0L),
                Arguments.of(new LottoScore(1, true), 0L),
                Arguments.of(new LottoScore(2, false), 0L),
                Arguments.of(new LottoScore(2, true), 0L),
                Arguments.of(new LottoScore(3, false), 5_000L),
                Arguments.of(new LottoScore(3, true), 5_000L),
                Arguments.of(new LottoScore(4, false), 50_000L),
                Arguments.of(new LottoScore(4, true), 50_000L),
                Arguments.of(new LottoScore(5, false), 1_500_000L),
                Arguments.of(new LottoScore(5, true), 30_000_000L),
                Arguments.of(new LottoScore(6, false), 2_000_000_000L)
        );
    }

    @ParameterizedTest
    @MethodSource("generateFormatPrizesData")
    void formatPrizesTest(List<LottoScore> lottoScore, String expected) {
        LottoPrize lottoPrize = new LottoPrize();
        String result = lottoPrize.formatPrizes(lottoScore);
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> generateFormatPrizesData() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new LottoScore(0, false)
                        ),
                        "3개 일치 (5000원) - 0개\n4개 일치 (50000원) - 0개\n5개 일치 (1500000원) - 0개\n5개 일치, 보너스 볼 일치 (30000000원) - 0개\n6개 일치 (2000000000원) - 0개"
                ),
                Arguments.of(
                        List.of(
                                new LottoScore(3, false),
                                new LottoScore(4, false),
                                new LottoScore(5, false),
                                new LottoScore(5, true),
                                new LottoScore(6, false)
                        ),
                        "3개 일치 (5000원) - 1개\n4개 일치 (50000원) - 1개\n5개 일치 (1500000원) - 1개\n5개 일치, 보너스 볼 일치 (30000000원) - 1개\n6개 일치 (2000000000원) - 1개"
                ),
                Arguments.of(
                        List.of(
                                new LottoScore(3, false),
                                new LottoScore(6, false),
                                new LottoScore(4, false),
                                new LottoScore(5, true),
                                new LottoScore(5, false),
                                new LottoScore(3, false),
                                new LottoScore(5, true),
                                new LottoScore(4, false),
                                new LottoScore(5, false),
                                new LottoScore(6, false)
                        ),
                        "3개 일치 (5000원) - 2개\n4개 일치 (50000원) - 2개\n5개 일치 (1500000원) - 2개\n5개 일치, 보너스 볼 일치 (30000000원) - 2개\n6개 일치 (2000000000원) - 2개"
                ),
                Arguments.of(
                        List.of(
                                new LottoScore(0, false),
                                new LottoScore(1, false),
                                new LottoScore(2, false),
                                new LottoScore(3, false)
                        ),
                        "3개 일치 (5000원) - 1개\n4개 일치 (50000원) - 0개\n5개 일치 (1500000원) - 0개\n5개 일치, 보너스 볼 일치 (30000000원) - 0개\n6개 일치 (2000000000원) - 0개"
                )
        );
    }
}
