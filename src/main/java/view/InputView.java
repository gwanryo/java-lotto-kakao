package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    private long money;
    private String lastWeekLottoIntsString;
    private int bonusNumber;
    private int manualTimes;
    private final List<String> manualLottoIntsStringList;

    public InputView() {
        this.scanner = new Scanner(System.in);
        this.manualLottoIntsStringList = new ArrayList<>();
    }

    public void inputMoney() {
        System.out.println("\n구매금액을 입력해 주세요.");
        money = Long.parseLong(scanner.nextLine());
    }

    public long getMoney() {
        return money;
    }

    public void inputManualTimes() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        manualTimes = Integer.parseInt(scanner.nextLine());
    }

    public int getManualTimes() {
        return manualTimes;
    }

    public void inputManualLottoIntsString() {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < manualTimes; i++) {
            manualLottoIntsStringList.add(scanner.nextLine());
        }
    }

    public List<String> getManualLottoIntsStringList() {
        return manualLottoIntsStringList;
    }

    public void inputLastWeekLottoIntsString() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        lastWeekLottoIntsString = scanner.nextLine();
    }

    public String getLastWeekLottoIntsString() {
        return lastWeekLottoIntsString;
    }

    public void inputBonusNumber() {
        System.out.println("\n보너스 볼을 입력해 주세요.");
        bonusNumber = Integer.parseInt(scanner.nextLine());
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
