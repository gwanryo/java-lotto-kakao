package controller;

import model.LottoDraw;
import model.LottoGame;
import model.LottoRandomNumbers;
import model.LottoResult;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;


    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public LottoGame purchase(int totalTimes) {
        if (totalTimes < 1) {
            System.exit(0);
        }

        LottoGame lottoGame = new LottoGame(new LottoRandomNumbers());

        inputView.inputManualTimes();
        int manualTimes = inputView.getManualTimes();

        if (totalTimes < manualTimes) {
            throw new RuntimeException(String.format("Can't buy more than %d times! (User input is %d times)", totalTimes, manualTimes));
        }

        inputView.inputManualLottoIntsString();
        List<String> manualLottoIntsStringList = inputView.getManualLottoIntsStringList();

        lottoGame.manual(manualLottoIntsStringList);

        int autoTimes = totalTimes - manualTimes;

        if (autoTimes > 0) {
            lottoGame.auto(autoTimes);
        }

        return lottoGame;
    }

    public void run() {
        inputView.inputMoney();
        long money = inputView.getMoney();
        int totalTimes = LottoGame.calcTimes(money);

        LottoGame lottoGame = purchase(totalTimes);

        int manualTimes = inputView.getManualTimes();

        outputView.printLottoPurchaseHistory(manualTimes, totalTimes - manualTimes);
        outputView.printLottoList(lottoGame);

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
