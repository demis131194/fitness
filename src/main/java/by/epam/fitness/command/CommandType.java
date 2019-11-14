package by.epam.fitness.command;

import by.epam.fitness.command.impl.*;
import by.epam.fitness.command.impl.locale.LocaleCommand;
import by.epam.fitness.command.impl.order.*;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    FIND_ALL_ORDERS_BY_CLIENT(new FindAllOrdersByClientCommand()),
    FIND_ALL_TRAINERS(new FindAllTrainersCommand()),
    SIGN_UP(new SignUpCommand()),
    FIND_ALL_COMMENTS(new FindAllCommentsCommand()),
    FIND_ORDER(new FindOrderCommand()),
    FIND_ORDERS_BY_FILTER(new FindAllOrdersByFilterCommand()),
    CREATE_NEW_ORDER(new CreateOrderCommand()),
    SHOW_NEW_ORDER_PAGE(new ShowCreateOrderPageCommand()),
    CHANGE_LOCALE(new LocaleCommand());

    CommandType(Command command) {
        this.command = command;
    }

    private Command command;

    public Command getCommand() {
        return command;
    }
}
