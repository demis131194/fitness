package by.epam.fitness.command;

import by.epam.fitness.command.impl.FindAllOrdersCommand;
import by.epam.fitness.command.impl.LoginCommand;
import by.epam.fitness.command.impl.LogoutCommand;

public enum CommandOperation {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    FIND_ALL_ORDERS(new FindAllOrdersCommand());

    CommandOperation(Command command) {
        this.command = command;
    }

    private Command command;

    public Command getCommand() {
        return command;
    }
}
