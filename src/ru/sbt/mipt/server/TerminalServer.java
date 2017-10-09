package ru.sbt.mipt.server;

import ru.sbt.mipt.exception.ConnectionProblemException;
import ru.sbt.mipt.exception.NotEnoughMoneyException;

public interface TerminalServer {
    int checkBalance() throws ConnectionProblemException;
    void addMoney(int sum) throws ConnectionProblemException;
    void getMoney(int sum) throws ConnectionProblemException, NotEnoughMoneyException;
}
