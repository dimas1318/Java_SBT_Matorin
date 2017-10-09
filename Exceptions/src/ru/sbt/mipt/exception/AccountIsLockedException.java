package ru.sbt.mipt.exception;

public class AccountIsLockedException extends Throwable {
    private final int remainingSeconds;

    public AccountIsLockedException(int remainingSeconds) {
        this.remainingSeconds = remainingSeconds;
    }

    public int getRemainingSeconds() {
        return remainingSeconds;
    }
}
