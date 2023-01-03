package model;

import java.util.List;
import java.util.stream.Collectors;

public class LottoResult {
    private final LottoGame lottoGame;
    private final WinningLotto winningLotto;
    private final LottoPrize lottoPrize;

    private long getTotalPrize() {
        return lottoGame.getLottoList().stream().mapToLong(l -> lottoPrize.getPrize(winningLotto.getScore(l))).sum();
    }

    private List<LottoScore> getLottoScores() {
        return lottoGame.getLottoList().stream().map(winningLotto::getScore).collect(Collectors.toList());
    }

    public LottoResult(LottoGame lottoGame, WinningLotto winningLotto) {
        this.lottoGame = lottoGame;
        this.winningLotto = winningLotto;
        this.lottoPrize = new LottoPrize();
    }

    public double getEarningRate(long money) {
        return (double) getTotalPrize() / money;
    }

    public String getResult() {
        return this.lottoPrize.formatPrizes(getLottoScores());
    }
}
