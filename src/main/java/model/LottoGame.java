package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoGame {
    private static final int LOTTO_COST = 1000;
    private final RandomNumbers randomNumbers;
    private List<Lotto> lottoList;

    public LottoGame(RandomNumbers randomNumbers) {
        this.randomNumbers = randomNumbers;
    }

    public void generate(int times) {
        lottoList = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            lottoList.add(new Lotto(randomNumbers.generate()));
        }
    }
    public List<Lotto> getLottoList() {
        return this.lottoList;
    }

    public int calcTimes(Long money){
        return (int)(money / LOTTO_COST);
    }

    @Override
    public String toString() {
        return lottoList.stream().map(Lotto::toString).collect(Collectors.joining("\n"));
    }
}
