package model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoDraw {
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private final List<LottoNumber> lottoNumbers;
    private final LottoNumber bonusNumber;

    public LottoDraw(String lottoString, int bonusNumber) {
        if (lottoString == null || lottoString.isEmpty()) {
            throw new IllegalArgumentException("Empty number is not allowed!");
        }

        List<Integer> lottoInts = Stream.of(lottoString.split(", "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        if (lottoInts.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(String.format("Cannot input more than %d numbers! (Input length: %d)", LOTTO_NUMBERS_SIZE, lottoInts.size()));
        }

        if (lottoInts.contains(bonusNumber)) {
            throw new IllegalArgumentException(String.format("Bonus number %d is already selected in Lotto!", bonusNumber));
        }

        this.lottoNumbers = lottoInts.stream().map(LottoNumber::new).collect(Collectors.toList());
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    public int getRank(Lotto lotto) {
        int matchNumber = (int) lotto.getNumbers().stream().filter(lottoNumbers::contains).count();
        boolean isMatchBonus = lotto.getNumbers().stream().anyMatch(i -> i.equals(bonusNumber));
        return new LottoScore(matchNumber, isMatchBonus).getRank();
    }
}
