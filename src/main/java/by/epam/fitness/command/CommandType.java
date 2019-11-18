package by.epam.fitness.command;

import by.epam.fitness.command.impl.*;
import by.epam.fitness.command.impl.admin.CreateTrainerCommand;
import by.epam.fitness.command.impl.admin.order.*;
import by.epam.fitness.command.impl.admin.user.*;
import by.epam.fitness.command.impl.client.ShowCreateCommentPageCommand;
import by.epam.fitness.command.impl.client.ShowCreateOrderPageCommand;
import by.epam.fitness.command.impl.client.ShowUpdatedOrderClientCommand;
import by.epam.fitness.command.impl.client.comment.CreateCommentCommand;
import by.epam.fitness.command.impl.client.order.*;
import by.epam.fitness.command.impl.locale.LocaleCommand;
import by.epam.fitness.command.impl.trainer.ShowUpdatedOrderTrainerCommand;
import by.epam.fitness.command.impl.trainer.order.FindAllOrdersByTrainerCommand;
import by.epam.fitness.command.impl.trainer.order.FindOrderByTrainerCommand;
import by.epam.fitness.command.impl.trainer.order.UpdateOrderByTrainerCommand;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    FIND_ALL_ORDERS_BY_CLIENT(new FindAllOrdersByClientCommand()),
    FIND_ALL_TRAINERS(new FindAllTrainersCommand()),
    SIGN_UP(new SignUpCommand()),
    FIND_ALL_COMMENTS(new FindAllCommentsCommand()),
    FIND_ORDER_BY_CLIENT(new FindOrderByClientCommand()),
    FIND_ORDER_BY_TRAINER(new FindOrderByTrainerCommand()),
    FIND_ORDERS_BY_FILTER(new FindAllOrdersByFilterClientCommand()),
    CREATE_NEW_ORDER(new CreateOrderCommand()),
    CREATE_NEW_COMMENT(new CreateCommentCommand()),
    SHOW_NEW_ORDER_PAGE(new ShowCreateOrderPageCommand()),
    CLIENT_SHOW_UPDATED_ORDER(new ShowUpdatedOrderClientCommand()),
    TRAINER_SHOW_UPDATED_ORDER(new ShowUpdatedOrderTrainerCommand()),
    SHOW_NEW_COMMENT_PAGE(new ShowCreateCommentPageCommand()),
    UPDATE_ORDER_BY_CLIENT(new UpdateOrderByClientCommand()),
    UPDATE_ORDER_BY_TRAINER(new UpdateOrderByTrainerCommand()),
    FIND_ALL_ORDERS_BY_TRAINER(new FindAllOrdersByTrainerCommand()),
    FIND_ALL_USERS_BY_ADMIN(new FindAllUsersByAdminCommand()),
    FIND_USER_BY_ADMIN(new FindUserByAdmin()),
    UPDATE_USER_BY_ADMIN(new UpdateUserByAdminCommand()),
    UPDATE_TRAINER_BY_ADMIN(new UpdateTrainerByAdminCommand()),
    UPDATE_CLIENT_BY_ADMIN(new UpdateClientByAdminCommand()),
    DELETE_USER_BY_ADMIN(new DeleteUserByAdminCommand()),
    RESTORE_USER_BY_ADMIN(new RestoreUserByAdminCommand()),
    FIND_USERS_BY_FILTER(new FindUsersByFilter()),
    CREATE_NEW_TRAINER(new CreateTrainerCommand()),
    FIND_ALL_ORDERS(new FindAllOrders()),
    FIND_ORDERS_BY_FILTER_BY_ADMIN(new FindUsersByFilterByAdmin()),
    FIND_ORDER_BY_ADMIN(new FindOrderByAdmin()),
    DELETE_ORDER_BY_ADMIN(new DeleteOrderByAdmin()),
    RESTORE_ORDER_BY_ADMIN(new RestoreOrderByAdmin()),
    CHANGE_LOCALE(new LocaleCommand());

    CommandType(Command command) {
        this.command = command;
    }

    private Command command;

    public Command getCommand() {
        return command;
    }
}
