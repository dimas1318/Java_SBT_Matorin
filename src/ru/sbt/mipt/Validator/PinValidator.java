package ru.sbt.mipt.Validator;

import ru.sbt.mipt.exception.AccountIsLockedException;
import ru.sbt.mipt.exception.InvalidPinException;

public interface PinValidator {
    void validatePin(String pin) throws InvalidPinException, AccountIsLockedException;
}
