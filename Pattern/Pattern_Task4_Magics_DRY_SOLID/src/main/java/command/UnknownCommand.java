package command;

import console.ConsoleHelper;

public class UnknownCommand implements Command {
    public static final String UNKNOWN_MESSAGE = "Неверно введена комманда.";

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(UNKNOWN_MESSAGE);
    }
}
