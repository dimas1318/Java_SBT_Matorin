package ru.sbt.mipt.server;

import ru.sbt.mipt.exception.ConnectionProblemException;
import ru.sbt.mipt.exception.NotEnoughMoneyException;

import java.util.Random;

public class TerminalServerImpl implements TerminalServer {
    private int balance;

    public TerminalServerImpl(int balance) {
        this.balance = balance;
    }

    @Override
    public int checkBalance() throws ConnectionProblemException {
        if (new Random().nextInt() % 10 == 2) {
            throw new ConnectionProblemException();
        }

        return balance;
    }

    @Override
    public void addMoney(int sum) throws ConnectionProblemException {
        if (new Random().nextInt() % 10 == 2) {
            throw new ConnectionProblemException();
        }

        balance += sum;
    }

    @Override
    public void getMoney(int sum) throws ConnectionProblemException, NotEnoughMoneyException {
        if (new Random().nextInt() % 10 == 2) {
            throw new ConnectionProblemException();
        }

        if (balance < sum) {
            throw new NotEnoughMoneyException();
        }

        balance -= sum;
    }
}
