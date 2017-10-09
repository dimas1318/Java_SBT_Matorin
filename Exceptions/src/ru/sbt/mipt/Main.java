package ru.sbt.mipt;

import ru.sbt.mipt.validator.PinValidator;
import ru.sbt.mipt.validator.PinValidatorImpl;
import ru.sbt.mipt.exception.*;
import ru.sbt.mipt.server.TerminalServer;
import ru.sbt.mipt.server.TerminalServerImpl;
import ru.sbt.mipt.terminal.Terminal;
import ru.sbt.mipt.terminal.TerminalImpl;

import java.util.Scanner;

public class Main {
    private static final int NO_CACHED_SUM = -1;

    public static void main(String[] args) {
        PinValidator validator = new PinValidatorImpl("0000");
        TerminalServer server = new TerminalServerImpl(15000);
        Terminal terminal = new TerminalImpl(server, validator);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the command:\n" +
                        "\"BALANCE\" - to display current balance\n" +
                        "\"ADD\" - to add money to account\n" +
                        "\"GET\" - to get money\n" +
                        "\"EXIT\" - to exit");

            String command = "";
            while (command.isEmpty()) {
                command = scanner.nextLine().toLowerCase().trim();
            };

            if (command.equals("exit")) {
                return;
            }

            executeCommand(command, terminal, scanner, NO_CACHED_SUM);
        }
    }

    private static void executeCommand(String command, Terminal terminal, Scanner scanner, int prevSum) {
        int sum = prevSum;
        switch (command) {
            case "balance":
                try {
                    System.out.println("Current balance - " + terminal.checkBalance() + "\n");
                } catch (NotValidatedPinException e) {
                    validate(scanner, terminal);
                    executeCommand(command, terminal, scanner, NO_CACHED_SUM);
                } catch (ConnectionProblemException e) {
                    System.out.println("Error: Connection is lost, try later.\n");
                }
                break;
            case "add":
                if (sum == NO_CACHED_SUM) {
                    System.out.println("Adding money. Enter sum:");
                    sum = scanner.nextInt();
                }

                try {
                    terminal.addMoney(sum);
                    System.out.println("Adding money. Success!\n");
                } catch (NotValidatedPinException e) {
                    validate(scanner, terminal);
                    executeCommand(command, terminal, scanner, sum);
                } catch (WrongSumException e) {
                    System.out.println("Error: Expected sum, which is multiple to 100 and is more than 0.\n");
                } catch (ConnectionProblemException e) {
                    System.out.println("Error: Connection is lost, try later.\n");
                }
                break;
            case "get":
                if (sum == NO_CACHED_SUM) {
                    System.out.println("Getting money. Enter sum:");
                    sum = scanner.nextInt();
                }

                try {
                    terminal.getMoney(sum);
                    System.out.println("Getting money. Success!\n");
                } catch (NotValidatedPinException e) {
                    validate(scanner, terminal);
                    executeCommand(command, terminal, scanner, sum);
                } catch (WrongSumException e) {
                    System.out.println("Error: Expected sum, which is multiple to 100 and is more than 0.\n");
                } catch (NotEnoughMoneyException e) {
                    System.out.println("Error: You have not enough money.\n");
                } catch (ConnectionProblemException e) {
                    System.out.println("Error: Connection is lost, try later.\n");
                }
                break;
            default:
                System.out.println("Unknown command.\n");
        }
    }

    private static void validate(Scanner in, Terminal terminal) {
        while (!terminal.isPinValidated()) {
            System.out.println("Please enter your PIN code:");
            try {
                terminal.validatePin(in.next());
            } catch (InvalidPinException e) {
                System.out.println("Error: Invalid PIN code. Remaining attempts: " + e.getRemainingAttempts() + "\n");
            } catch (AccountIsLockedException e) {
                System.out.println("Error: Your card is locked for " + e.getRemainingSeconds() + " seconds.\n");
            }
        }
    }
}
