package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoGame {
    private static final int LOTTO_COST = 1000;
    private final LottoRandomNumbers lottoRandomNumbers;
    private final List<Lotto> lottoList;

    public LottoGame(LottoRandomNumbers lottoRandomNumbers) {
        this.lottoRandomNumbers = lottoRandomNumbers;
        this.lottoList = new ArrayList<>();
    }

    public void manual(List<String> lottoIntsStringList) {
        if (lottoIntsStringList == null || lottoIntsStringList.isEmpty()) {
            throw new IllegalArgumentException("Input lotto shall not be null or empty in manual mode!");
        }

        lottoIntsStringList.forEach(lottoIntsString -> {
            List<Integer> lottoInts = Stream.of(lottoIntsString.split(", "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
            lottoList.add(new Lotto(lottoInts));
        });
    }

    public void auto(int times) {
        for (int i = 0; i < times; i++) {
            lottoList.add(new Lotto(lottoRandomNumbers.generate()));
        }
    }

    public List<Lotto> getLottoList() {
        return this.lottoList;
    }

    public int calcTimes(Long money) {
        return (int) (money / LOTTO_COST);
    }

    @Override
    public String toString() {
        return lottoList.stream().map(Lotto::toString).collect(Collectors.joining("\n"));
    }
}
