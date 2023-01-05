package controller;

import model.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGame lottoGame;


    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoGame = new LottoGame(new LottoRandomNumbers());
    }

    public void purchase(long money) {
        int times = lottoGame.calcTimes(money);

        if (times < 1) {
            return;
        }

        inputView.inputManualTimes();
        int manualTimes = inputView.getManualTimes();

        if (times < manualTimes) {
            throw new RuntimeException(String.format("Can't buy more than %d times! (User input is %d times)", times, manualTimes));
        }

        inputView.inputManualLottoIntsString();
        List<String> manualLottoIntsStringList = inputView.getManualLottoIntsStringList();

        lottoGame.manual(manualLottoIntsStringList);

        int autoTimes = times - manualTimes;

        if (autoTimes > 0) {
            lottoGame.auto(times);
        }

        outputView.printLottoPurchaseHistory(manualTimes, autoTimes);
        outputView.printLottoList(lottoGame);
    }

    public void run() {
        inputView.inputMoney();
        long money = inputView.getMoney();

        purchase(money);

        inputView.inputLastWeekLottoIntsString();
        String lottoString = inputView.getLastWeekLottoIntsString();

        inputView.inputBonusNumber();
        int bonus = inputView.getBonusNumber();

        LottoDraw lottoDraw = new LottoDraw(lottoString, bonus);
        LottoResult lottoResult = new LottoResult(lottoGame, lottoDraw);

        outputView.printResult(lottoResult);
        outputView.printEarningRate(lottoResult.getEarningRate(money));
    }
}
