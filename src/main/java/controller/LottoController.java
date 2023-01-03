package controller;

import model.LottoDraw;
import model.LottoGame;
import model.LottoRandomNumbers;
import model.LottoResult;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;


    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        long money = inputView.inputMoney();
        LottoGame lottoGame = new LottoGame(new LottoRandomNumbers());

        int times = lottoGame.calcTimes(money);
        outputView.putTimes(times);

        if (times < 1) {
            return;
        }

        lottoGame.auto(times);
        outputView.printLottos(lottoGame);

        String lottoString = inputView.getLottoString();
        int bonus = inputView.getBonus();

        LottoDraw lottoDraw = new LottoDraw(lottoString, bonus);
        LottoResult lottoResult = new LottoResult(lottoGame, lottoDraw);

        outputView.printResult(lottoResult);
        outputView.printEarningRate(lottoResult.getEarningRate(money));
    }
}
