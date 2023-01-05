package model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        if (lottoNumbers == null || lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("Lotto must select 6 numbers!");
        }

        Set<Integer> lottoNumbersWithoutDuplicates = new LinkedHashSet<>(lottoNumbers);
        if (lottoNumbers.size() != lottoNumbersWithoutDuplicates.size()) {
            throw new IllegalArgumentException("Duplicated numbers not allowed!");
        }

        this.lottoNumbers = lottoNumbers.stream().map(LottoNumber::new).collect(Collectors.toList());
        Collections.sort(this.lottoNumbers);
    }

    public List<LottoNumber> getNumbers() {
        return lottoNumbers;
    }

    @Override
    public String toString() {
        return "[" + lottoNumbers.stream().map(LottoNumber::toString).collect(Collectors.joining(", ")) + "]";
    }
}
