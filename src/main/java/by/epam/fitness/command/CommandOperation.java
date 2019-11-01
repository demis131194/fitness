package by.epam.fitness.command;

import by.epam.fitness.command.impl.FindAllOrdersCommand;
import by.epam.fitness.command.impl.FindAllTrainers;
import by.epam.fitness.command.impl.LoginCommand;
import by.epam.fitness.command.impl.LogoutCommand;

public enum CommandOperation {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    FIND_ALL_ORDERS(new FindAllOrdersCommand()),
    FIND_ALL_TRAINERS(new FindAllTrainers());

    CommandOperation(Command command) {
        this.command = command;
    }

    private Command command;

    public Command getCommand() {
        return command;
    }
}
