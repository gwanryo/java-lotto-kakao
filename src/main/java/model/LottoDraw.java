package model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoDraw {
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private final Lotto lotto;
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

        this.lotto = new Lotto(lottoInts);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    public int getRank(Lotto lotto) {
        int matchNumber = (int) lotto.getNumbers().stream().filter(lottoNumber -> this.lotto.getNumbers().contains(lottoNumber)).count();
        boolean isMatchBonus = lotto.getNumbers().stream().anyMatch(i -> i.equals(bonusNumber));
        return calcRank(matchNumber, isMatchBonus);
    }

    private int calcRank(int matchCount, boolean isMatchBonus) {
        if (matchCount < 0 || matchCount > 6 || (matchCount == 6 && isMatchBonus)) {
            throw new IllegalArgumentException("Match count must be in range 0 to 6! (Bonus must be false when score is 6)");
        }

        return switch (matchCount) {
            case 5 -> matchCount + Boolean.compare(isMatchBonus, false);
            case 6 -> matchCount + 1;
            default -> matchCount;
        };
    }

    public static String getRankResult(int rank, long prizeMoney) {
        return switch (rank) {
            case 6 -> String.format("%d개 일치, 보너스 볼 일치 (%d원) - ", rank - 1, prizeMoney);
            case 7 -> String.format("%d개 일치 (%d원) - ", rank - 1, prizeMoney);
            default -> String.format("%d개 일치 (%d원) - ", rank, prizeMoney);
        };
    }
}
