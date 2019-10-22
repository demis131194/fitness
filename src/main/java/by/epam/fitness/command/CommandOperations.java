package by.epam.fitness.command;

import by.epam.fitness.command.comands.FindAllOrdersCommand;
import by.epam.fitness.command.comands.LoginCommand;
import by.epam.fitness.command.comands.LogoutCommand;

public enum CommandOperations {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    FIND_ALL_ORDERS(new FindAllOrdersCommand());

    CommandOperations(Command command) {
        this.command = command;
    }

    private Command command;

    public Command getCommand() {
        return command;
    }
}
