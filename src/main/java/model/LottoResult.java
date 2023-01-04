package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static model.LottoScore.getResultByRank;

public class LottoResult {
    private static final LinkedHashMap<Integer, Long> prize = new LinkedHashMap<>() {{
        put(3, 5000L);
        put(4, 50_000L);
        put(5, 1_500_000L);
        put(6, 30_000_000L);
        put(7, 2_000_000_000L);
    }};

    private final LottoGame lottoGame;
    private final LottoDraw lottoDraw;

    public LottoResult(LottoGame lottoGame, LottoDraw lottoDraw) {
        this.lottoGame = lottoGame;
        this.lottoDraw = lottoDraw;
    }

    public double getEarningRate(long money) {
        long totalPrize = lottoGame.getLottoList().stream().mapToLong(l -> prize.getOrDefault(lottoDraw.getRank(l), 0L)).sum();
        return (double) totalPrize / money;
    }

    public String getResult() {
        List<Integer> ranks = lottoGame.getLottoList().stream().map(lottoDraw::getRank).collect(Collectors.toList());
        List<String> prizeResults = new ArrayList<>();

        prize.forEach((rank, prizeMoney) -> {
            prizeResults.add(getResultByRank(rank, prizeMoney) + ranks.stream().filter(r -> r.equals(rank)).count() + "개");
        });

        return String.join("\n", prizeResults);
    }
}
