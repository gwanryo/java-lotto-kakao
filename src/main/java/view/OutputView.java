package view;

import model.LottoGame;
import model.LottoResult;

public class OutputView {
    public void putTimes(int times) {
        System.out.println(times + "개를 구매했습니다.");
    }

    public void printLottos(LottoGame lottoGame) {
        lottoGame.getLottoList().forEach(lotto -> System.out.println(lotto.toString()));
        System.out.println();
    }

    public void printResult(LottoResult lottoResult) {
        System.out.println("당첨 통계");
        System.out.println("-------");
        System.out.println(lottoResult.getResult() + "\n");
    }

    public void printEarningRate(double earningRate) {
        System.out.printf("총 수익률은 %.2f입니다.\n", earningRate);
        if (earningRate < 1.0) {
            System.out.println("손해!");
            return;
        }
        System.out.println("이득!");
    }
}
