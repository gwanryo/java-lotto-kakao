package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 15, 20, 25, 30, 35, 40, 45})
    @DisplayName("로또 숫자 범위 내에서 정상적으로 객체 생성")
    void lottoNumberTest(int number) {
        assertThatCode(() -> new LottoNumber(number)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, -1, 0, 46, 47, 50})
    @DisplayName("로또 숫자 범위를 벗어나면 예외 발생")
    void invalidLottoNumberTest(int number) {
        assertThatThrownBy(() -> new LottoNumber(number)).isInstanceOf(IllegalArgumentException.class);
    }
}
