package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    @RepeatedTest(100)
    @DisplayName("랜덤 생성한 숫자를 기준으로 로또를 생성했을 때 정상적으로 생성된다.")
    void generateNumbersTest() {
        LottoRandomNumbers lottoRandomNumbers = new LottoRandomNumbers();
        List<Integer> generatedNumbers = lottoRandomNumbers.generate();
        Lotto lotto = new Lotto(generatedNumbers);
        Collections.sort(generatedNumbers);
        assertThat(lotto.getNumbers()).isEqualTo(
                generatedNumbers.stream()
                        .map(LottoNumber::new)
                        .collect(Collectors.toList())
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("null 혹은 빈 값 입력 시 예외를 발생")
    void lottoNumbersNullOrEmptyTest(List<Integer> lottoNumbers) {
        assertThatThrownBy(() -> new Lotto(lottoNumbers)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("generateInvalidLengthOfLottoNumbersData")
    @DisplayName("6개 이상의 로또 당첨 번호를 입력하면 예외를 발생")
    void invalidLengthOfLottoNumbersTest(List<Integer> lottoNumbers) {
        assertThatThrownBy(() -> new Lotto(lottoNumbers)).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> generateInvalidLengthOfLottoNumbersData() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7)),
                Arguments.of(List.of(1)),
                Arguments.of(List.of())
        );
    }

    @ParameterizedTest
    @MethodSource("generateDuplicatedLottoNumbersData")
    @DisplayName("6개 이상의 로또 당첨 번호를 입력하면 예외를 발생")
    void duplicatedLottoNumbersTest(List<Integer> lottoNumbers) {
        assertThatThrownBy(() -> new Lotto(lottoNumbers)).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> generateDuplicatedLottoNumbersData() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 6, 6)),
                Arguments.of(List.of(1, 1, 2, 3, 4, 5))
        );
    }
}
