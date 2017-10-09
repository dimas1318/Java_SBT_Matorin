package ru.sbt.mipt.terminal;

import ru.sbt.mipt.exception.*;

public interface Terminal {
    int checkBalance() throws NotValidatedPinException, ConnectionProblemException;
    void addMoney(int sum) throws NotValidatedPinException, WrongSumException, ConnectionProblemException;
    void getMoney(int sum) throws NotValidatedPinException, WrongSumException, NotEnoughMoneyException,
            ConnectionProblemException;
    void validatePin(String pin) throws InvalidPinException, AccountIsLockedException;
    boolean isPinValidated();
}
