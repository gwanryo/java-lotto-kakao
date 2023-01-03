package model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WinningLotto {
    private final List<LottoNumber> lottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(String lottoString, int bonusNumber) {
        if (lottoString == null || lottoString.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.lottoNumbers = Stream.of(lottoString.split(", "))
                .mapToInt(Integer::parseInt)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    public LottoScore getScore(Lotto lotto) {
        int matchNumber = (int) lotto.getNumbers().stream().filter(lottoNumbers::contains).count();
        boolean isMatchBonus = lotto.getNumbers().stream().anyMatch(i -> i.equals(bonusNumber));
        return new LottoScore(matchNumber, isMatchBonus);
    }
}
