package model;

import java.util.List;
import java.util.stream.Collectors;

public class LottoResult {
    private final LottoGame lottoGame;
    private final LottoDraw lottoDraw;
    private final LottoPrize lottoPrize;

    public LottoResult(LottoGame lottoGame, LottoDraw lottoDraw) {
        this.lottoGame = lottoGame;
        this.lottoDraw = lottoDraw;
        this.lottoPrize = new LottoPrize();
    }

    private long getTotalPrize() {
        return lottoGame.getLottoList().stream().mapToLong(l -> lottoPrize.getPrize(lottoDraw.getScore(l))).sum();
    }

    private List<LottoScore> getLottoScores() {
        return lottoGame.getLottoList().stream().map(lottoDraw::getScore).collect(Collectors.toList());
    }

    public double getEarningRate(long money) {
        return (double) getTotalPrize() / money;
    }

    public String getResult() {
        return this.lottoPrize.formatPrizes(getLottoScores());
    }
}
