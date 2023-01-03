package model;

import java.util.Objects;

public class LottoScore {
    private final int matchCount;
    private final boolean isMatchBonus;

    public LottoScore(int matchCount, boolean isMatchBonus) {
        if (matchCount < 0 || matchCount > 6 || (matchCount == 6 && isMatchBonus)) {
            throw new IllegalArgumentException();
        }

        this.matchCount = matchCount;
        this.isMatchBonus = isMatchBonus;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isMatchBonus() {
        return isMatchBonus;
    }

    public boolean compare(LottoScore that) {
        if (this.matchCount != that.matchCount) {
            return false;
        }

        if (this.matchCount != 5) {
            return true;
        }

        return this.isMatchBonus == that.isMatchBonus;
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
