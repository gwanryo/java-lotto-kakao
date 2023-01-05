package view;

import model.LottoGame;
import model.LottoResult;

public class OutputView {
    public void printLottoPurchaseHistory(int manualTimes, int autoTimes) {
        System.out.printf("\n수동으로 %d장, 자동으로 %d장을 구매했습니다.\n", manualTimes, autoTimes);
    }

    public void printLottoList(LottoGame lottoGame) {
        lottoGame.getLottoList().forEach(lotto -> System.out.println(lotto.toString()));
        System.out.println();
    }

    public void printResult(LottoResult lottoResult) {
        System.out.println("\n당첨 통계");
        System.out.println("-------");
        System.out.println(lottoResult.getResult());
    }

    public void printEarningRate(double earningRate) {
        System.out.printf("\n총 수익률은 %.2f입니다.", earningRate);
        if (earningRate < 1.0) {
            System.out.println("\n손해!");
            return;
        }
        System.out.println("\n이득!");
    }
}
