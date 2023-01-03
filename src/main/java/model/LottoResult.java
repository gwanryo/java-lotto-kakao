package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class LottoResult {
    private static final LinkedHashMap<LottoScore, Long> prize = new LinkedHashMap<>() {{
        put(new LottoScore(3, false), 5000L);
        put(new LottoScore(4, false), 50_000L);
        put(new LottoScore(5, false), 1_500_000L);
        put(new LottoScore(5, true), 30_000_000L);
        put(new LottoScore(6, false), 2_000_000_000L);
    }};

    private final LottoGame lottoGame;
    private final LottoDraw lottoDraw;

    public LottoResult(LottoGame lottoGame, LottoDraw lottoDraw) {
        this.lottoGame = lottoGame;
        this.lottoDraw = lottoDraw;
    }

    private String formatPrize(LottoScore lottoScore) {
        if (lottoScore.getMatchCount() == 5 && lottoScore.isMatchBonus()) {
            return String.format("%d개 일치, 보너스 볼 일치 (%d원) - ", lottoScore.getMatchCount(), getPrize(lottoScore));
        }
        return String.format("%d개 일치 (%d원) - ", lottoScore.getMatchCount(), getPrize(lottoScore));
    }

    private Long getPrize(LottoScore lottoScore) {
        if (lottoScore.getMatchCount() == 5) {
            return prize.getOrDefault(lottoScore, 0L);
        }
        return prize.getOrDefault(new LottoScore(lottoScore.getMatchCount(), false), 0L);
    }

    private long getTotalPrize() {
        return lottoGame.getLottoList().stream().mapToLong(l -> getPrize(lottoDraw.getScore(l))).sum();
    }

    public double getEarningRate(long money) {
        return (double) getTotalPrize() / money;
    }

    public String getResult() {
        List<LottoScore> lottoScores = lottoGame.getLottoList().stream().map(lottoDraw::getScore).collect(Collectors.toList());
        List<String> prizeResults = new ArrayList<>();
        prize.forEach((lottoScore, prizeMoney) -> {
            long count = lottoScores.stream().filter(l -> l.compare(lottoScore)).count();
            prizeResults.add(formatPrize(lottoScore) + count + "개");
        });
        return String.join("\n", prizeResults);
    }
}
