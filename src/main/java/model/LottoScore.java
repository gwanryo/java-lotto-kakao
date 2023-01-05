package model;

import java.util.Objects;

public class LottoScore {
    private final int matchCount;
    private final boolean isMatchBonus;
    private final int rank;

    public LottoScore(int matchCount, boolean isMatchBonus) {
        this.matchCount = matchCount;
        this.isMatchBonus = isMatchBonus;
        this.rank = rank();
    }

    public int getRank() {
        return rank;
    }

    private int rank() {
        if (matchCount < 0 || matchCount > 6 || (matchCount == 6 && isMatchBonus)) {
            throw new IllegalArgumentException("LottoScore must be in range 0 to 6! (Bonus must be false when score is 6)");
        }

        switch (matchCount) {
            case 5:
                return matchCount + Boolean.compare(isMatchBonus, false);
            case 6:
                return matchCount + 1;
            default:
                return matchCount;
        }
    }

    public static String getResultByRank(int rank, long prizeMoney) {
        switch (rank) {
            case 6:
                return String.format("%d개 일치, 보너스 볼 일치 (%d원) - ", rank - 1, prizeMoney);
            case 7:
                return String.format("%d개 일치 (%d원) - ", rank - 1, prizeMoney);
            default:
                return String.format("%d개 일치 (%d원) - ", rank, prizeMoney);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoScore that = (LottoScore) o;
        return matchCount == that.matchCount && isMatchBonus == that.isMatchBonus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchCount, isMatchBonus);
    }
}
