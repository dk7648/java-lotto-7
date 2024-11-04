package lotto.view;

import lotto.model.Lotto;

import java.util.*;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class InputView {
    private static final String UNIQUENESS = "[ERROR] 로또 번호는 중복되지 않아야 합니다.";
    private static final String OUT_OF_RANGE = "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.";
    public static int readMoney() {
        String input = readLine();
        try {
            return convertInputToMoney(input);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorOfRequestMoney();
            return readMoney();
        }
    }

    private static int convertInputToMoney(String input) throws IllegalArgumentException {
        int money = Integer.parseInt(input);
        if (money < 0) {
            throw new IllegalArgumentException();
        }
        return money;
    }

    public static Lotto readWinningNumbers() {
        String input = readLine();
        try {
            return convertInputToWinningNumbers(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            OutputView.printErrorOfRequestNumbers();
            return readWinningNumbers();
        }
    }

    private static Lotto convertInputToWinningNumbers(String input) throws IllegalArgumentException {
        String trimmedInput = input.replaceAll(" ", "");
        List<Integer> Numbers = new ArrayList<>();
        for (String rawNumber : trimmedInput.split(",")) {
            Numbers.add(Integer.parseInt(rawNumber));
        }
        return new Lotto(Numbers);
    }

    public static int readBonusNumber(Lotto winningNumbers) {
        String input = readLine();
        try {
            return convertInputToNumber(input, winningNumbers);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            OutputView.printErrorOfRequestNumber();
            return readBonusNumber(winningNumbers);
        }
    }

    private static int convertInputToNumber(String input, Lotto winningNumbers) throws IllegalArgumentException {
        int bonusNumber = Integer.parseInt(input);
        validate(bonusNumber, winningNumbers);
        return bonusNumber;
    }
    private static void validate(int bonusNumber, Lotto winningNumbers) {
        validateUniqueness(bonusNumber, winningNumbers);
        validateOutOfRange(bonusNumber,winningNumbers);
    }
    private static void validateUniqueness(int bonusNumber, Lotto winningNumbers) {
        if(winningNumbers.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(UNIQUENESS);
        }
    }
    private static void validateOutOfRange(int bonusNumber, Lotto winningNumbers) {
        if(bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException(OUT_OF_RANGE);
        }
    }
}
