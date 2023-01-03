package view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    private long money;
    private String lastWeekLottoNumbersString;
    private int bonusNumber;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public void inputMoney() {
        System.out.println("구매금액을 입력해 주세요.");
        money = Long.parseLong(scanner.nextLine());
    }

    public long getMoney() {
        return money;
    }

    public void inputLastWeekLottoNumbersString() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        lastWeekLottoNumbersString = scanner.nextLine();
    }

    public String getLastWeekLottoNumbersString() {
        return lastWeekLottoNumbersString;
    }

    public void inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        bonusNumber = Integer.parseInt(scanner.nextLine());
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
