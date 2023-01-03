package controller;

import model.LottoResult;
import model.LottoGame;
import model.RandomNumbers;
import model.WinningLotto;
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
        LottoGame lottoGame = new LottoGame(new RandomNumbers());

        int times = lottoGame.calcTimes(money);
        outputView.putTimes(times);

        if (times < 1) {
            return;
        }

        lottoGame.generate(times);
        outputView.printLottos(lottoGame);

        String lottoString = inputView.getLottoString();
        int bonus = inputView.getBonus();

        WinningLotto winningLotto = new WinningLotto(lottoString, bonus);
        LottoResult lottoResult = new LottoResult(lottoGame, winningLotto);

        outputView.printResult(lottoResult);
        outputView.printEarningRate(lottoResult.getEarningRate(money));
    }
}
