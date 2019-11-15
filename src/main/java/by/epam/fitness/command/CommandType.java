package by.epam.fitness.command;

import by.epam.fitness.command.impl.*;
import by.epam.fitness.command.impl.client.*;
import by.epam.fitness.command.impl.client.ShowCreateCommentPageCommand;
import by.epam.fitness.command.impl.client.order.*;
import by.epam.fitness.command.impl.locale.LocaleCommand;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    FIND_ALL_ORDERS_BY_CLIENT(new FindAllOrdersByClientCommand()),
    FIND_ALL_TRAINERS(new FindAllTrainersCommand()),
    SIGN_UP(new SignUpCommand()),
    FIND_ALL_COMMENTS(new FindAllCommentsCommand()),
    FIND_ORDER(new FindOrderCommand()),
    FIND_ORDERS_BY_FILTER(new FindAllOrdersByFilterClientCommand()),
    CREATE_NEW_ORDER(new CreateOrderCommand()),
    CREATE_NEW_COMMENT(new CreateCommentCommand()),
    SHOW_NEW_ORDER_PAGE(new ShowCreateOrderPageCommand()),
    SHOW_UPDATED_ORDER(new ShowUpdatedOrderCommand()),
    SHOW_NEW_COMMENT_PAGE(new ShowCreateCommentPageCommand()),
    UPDATE_ORDER_BY_CLIENT(new UpdateOrderByClientCommand()),
    CHANGE_LOCALE(new LocaleCommand());

    CommandType(Command command) {
        this.command = command;
    }

    private Command command;

    public Command getCommand() {
        return command;
    }
}
