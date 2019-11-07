package by.epam.fitness.command;

import by.epam.fitness.command.impl.*;
import by.epam.fitness.command.impl.locale.LocaleCommand;

public enum CommandOperation {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    FIND_ALL_ORDERS(new FindAllOrdersCommand()),
    FIND_ALL_TRAINERS(new FindAllTrainersCommand()),
    SIGN_UP(new SignUpCommand()),
    CHANGE_LOCALE(new LocaleCommand());

    CommandOperation(Command command) {
        this.command = command;
    }

    private Command command;

    public Command getCommand() {
        return command;
    }
}
