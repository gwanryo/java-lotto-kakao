package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGameTest {
    @Test
    @DisplayName("지정한 횟수만큼 로또 객체를 배열로 생성")
    void lottoGameLengthTest() {
        LottoGame lottoGame = new LottoGame(new LottoRandomNumbers());
        lottoGame.auto(10);
        List<Lotto> lottoList = lottoGame.getLottoList();
        assertThat(lottoList.size()).isEqualTo(10);
    }

    @ParameterizedTest
    @MethodSource("generateLottoGameResultData")
    @DisplayName("랜덤 생성한 숫자로 로또를 생성했을 때 정렬된 상태로 생성")
    void lottoGameResultTest(List<Integer> randomNumbers, int times, String expected) {
        LottoGame lottoGame = new LottoGame(createRandomNumbers(randomNumbers));
        lottoGame.auto(times);
        assertThat(lottoGame.toString()).isEqualTo(expected);
    }

    static Stream<Arguments> generateLottoGameResultData() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 1, "[1, 2, 3, 4, 5, 6]"),
                Arguments.of(List.of(6, 5, 4, 3, 2, 1), 2, "[1, 2, 3, 4, 5, 6]\n[1, 2, 3, 4, 5, 6]"),
                Arguments.of(List.of(10, 11, 12, 13, 14, 15), 1, "[10, 11, 12, 13, 14, 15]")
        );
    }

    @ParameterizedTest
    @MethodSource("generateTimesData")
    @DisplayName("랜덤 생성한 숫자로 로또를 생성했을 때 정렬된 상태로 생성")
    void lottosResultTest(long money, int expectedTimes) {
        LottoGame lottoGame = new LottoGame(new LottoRandomNumbers());
        int times = lottoGame.calcTimes(money);
        assertThat(times).isEqualTo(expectedTimes);
    }

    static Stream<Arguments> generateTimesData() {
        return Stream.of(
                Arguments.of(1000L, 1),
                Arguments.of(2345L, 2),
                Arguments.of(5500L, 5)
        );
    }

    private LottoRandomNumbers createRandomNumbers(List<Integer> returnValue) {
        return new LottoRandomNumbers() {
            public List<Integer> generate() {
                return returnValue;
            }
        };
    }
}
