package by.epam.fitness.command;

import by.epam.fitness.command.impl.*;
import by.epam.fitness.command.impl.admin.CreateTrainerCommand;
import by.epam.fitness.command.impl.admin.EditPasswordByAdminCommand;
import by.epam.fitness.command.impl.admin.LoadProfileImageByAdminCommand;
import by.epam.fitness.command.impl.admin.UpdateProfileByAdminCommand;
import by.epam.fitness.command.impl.admin.comment.DeleteCommentByAdmin;
import by.epam.fitness.command.impl.admin.comment.FindAllCommentsByAdmin;
import by.epam.fitness.command.impl.admin.comment.FindCommentsByFilterByAdmin;
import by.epam.fitness.command.impl.admin.comment.RestoreCommentByAdmin;
import by.epam.fitness.command.impl.admin.order.*;
import by.epam.fitness.command.impl.admin.user.*;
import by.epam.fitness.command.impl.client.*;
import by.epam.fitness.command.impl.client.cash.DepositByClientCommand;
import by.epam.fitness.command.impl.client.comment.CreateCommentCommand;
import by.epam.fitness.command.impl.client.order.*;
import by.epam.fitness.command.impl.locale.LocaleCommand;
import by.epam.fitness.command.impl.trainer.EditPasswordByTrainerCommand;
import by.epam.fitness.command.impl.trainer.LoadProfileImageByTrainerCommand;
import by.epam.fitness.command.impl.trainer.ShowUpdatedOrderTrainerCommand;
import by.epam.fitness.command.impl.trainer.UpdateProfileByTrainerCommand;
import by.epam.fitness.command.impl.trainer.clients.FindAllClientsByTrainerCommand;
import by.epam.fitness.command.impl.trainer.order.FindAllOrdersByTrainerCommand;
import by.epam.fitness.command.impl.trainer.order.FindOrderByTrainerCommand;
import by.epam.fitness.command.impl.trainer.order.UpdateOrderByTrainerCommand;

/**
 * The enum Command type.
 */
public enum CommandType {
    /**
     * The Login.
     */
    LOGIN(new LoginCommand()),
    /**
     * The Logout.
     */
    LOGOUT(new LogoutCommand()),
    /**
     * The Find all orders by client.
     */
    FIND_ALL_ORDERS_BY_CLIENT(new FindAllOrdersByClientCommand()),
    /**
     * The Find all trainers.
     */
    FIND_ALL_TRAINERS(new FindAllTrainersCommand()),
    /**
     * The Sign up.
     */
    SIGN_UP(new SignUpCommand()),
    /**
     * The Find all comments.
     */
    FIND_ALL_COMMENTS(new FindAllCommentsCommand()),
    /**
     * The Find order by client.
     */
    FIND_ORDER_BY_CLIENT(new FindOrderByClientCommand()),
    /**
     * The Delete order by client.
     */
    DELETE_ORDER_BY_CLIENT(new DeleteOrderByClient()),
    /**
     * The Find order by trainer.
     */
    FIND_ORDER_BY_TRAINER(new FindOrderByTrainerCommand()),
    /**
     * The Find orders by filter.
     */
    FIND_ORDERS_BY_FILTER(new FindAllOrdersByFilterClientCommand()),
    /**
     * The Create new order.
     */
    CREATE_NEW_ORDER(new CreateOrderCommand()),
    /**
     * The Create new comment.
     */
    CREATE_NEW_COMMENT(new CreateCommentCommand()),
    /**
     * The Show new order page.
     */
    SHOW_NEW_ORDER_PAGE(new ShowCreateOrderPageCommand()),
    /**
     * The Edit profile by client.
     */
    EDIT_PROFILE_BY_CLIENT(new UpdateProfileByClientCommand()),
    /**
     * The Client show updated order.
     */
    CLIENT_SHOW_UPDATED_ORDER(new ShowUpdatedOrderClientCommand()),
    /**
     * The Deposit by client.
     */
    DEPOSIT_BY_CLIENT(new DepositByClientCommand()),
    /**
     * The Trainer show updated order.
     */
    TRAINER_SHOW_UPDATED_ORDER(new ShowUpdatedOrderTrainerCommand()),
    /**
     * The Show new comment page.
     */
    SHOW_NEW_COMMENT_PAGE(new ShowCreateCommentPageCommand()),
    /**
     * The Update order by client.
     */
    UPDATE_ORDER_BY_CLIENT(new UpdateOrderByClientCommand()),
    /**
     * The Update order by trainer.
     */
    UPDATE_ORDER_BY_TRAINER(new UpdateOrderByTrainerCommand()),
    /**
     * The Find all orders by trainer.
     */
    FIND_ALL_ORDERS_BY_TRAINER(new FindAllOrdersByTrainerCommand()),
    /**
     * The Find all users by admin.
     */
    FIND_ALL_USERS_BY_ADMIN(new FindAllUsersByAdminCommand()),
    /**
     * The Find user by admin.
     */
    FIND_USER_BY_ADMIN(new FindUserByAdmin()),
    /**
     * The Update user by admin.
     */
    UPDATE_USER_BY_ADMIN(new UpdateUserByAdminCommand()),
    /**
     * The Update trainer by admin.
     */
    UPDATE_TRAINER_BY_ADMIN(new UpdateTrainerByAdminCommand()),
    /**
     * The Update client by admin.
     */
    UPDATE_CLIENT_BY_ADMIN(new UpdateClientByAdminCommand()),
    /**
     * The Delete user by admin.
     */
    DELETE_USER_BY_ADMIN(new DeleteUserByAdminCommand()),
    /**
     * The Restore user by admin.
     */
    RESTORE_USER_BY_ADMIN(new RestoreUserByAdminCommand()),
    /**
     * The Find users by filter.
     */
    FIND_USERS_BY_FILTER(new FindUsersByFilter()),
    /**
     * The Create new trainer.
     */
    CREATE_NEW_TRAINER(new CreateTrainerCommand()),
    /**
     * The Find all orders.
     */
    FIND_ALL_ORDERS(new FindAllOrders()),
    /**
     * The Find orders by filter by admin.
     */
    FIND_ORDERS_BY_FILTER_BY_ADMIN(new FindOrdersByFilterByAdmin()),
    /**
     * The Find order by admin.
     */
    FIND_ORDER_BY_ADMIN(new FindOrderByAdmin()),
    /**
     * The Delete order by admin.
     */
    DELETE_ORDER_BY_ADMIN(new DeleteOrderByAdmin()),
    /**
     * The Restore order by admin.
     */
    RESTORE_ORDER_BY_ADMIN(new RestoreOrderByAdmin()),
    /**
     * The Find all comments by admin.
     */
    FIND_ALL_COMMENTS_BY_ADMIN(new FindAllCommentsByAdmin()),
    /**
     * The Delete comment by admin.
     */
    DELETE_COMMENT_BY_ADMIN(new DeleteCommentByAdmin()),
    /**
     * The Restore comment by admin.
     */
    RESTORE_COMMENT_BY_ADMIN(new RestoreCommentByAdmin()),
    /**
     * The Find comments by filter by admin.
     */
    FIND_COMMENTS_BY_FILTER_BY_ADMIN(new FindCommentsByFilterByAdmin()),
    /**
     * The Edit password by client.
     */
    EDIT_PASSWORD_BY_CLIENT(new EditPasswordByClientCommand()),
    /**
     * The Edit password by trainer.
     */
    EDIT_PASSWORD_BY_TRAINER(new EditPasswordByTrainerCommand()),
    /**
     * The Edit profile by trainer.
     */
    EDIT_PROFILE_BY_TRAINER(new UpdateProfileByTrainerCommand()),
    /**
     * The Edit profile by admin.
     */
    EDIT_PROFILE_BY_ADMIN(new UpdateProfileByAdminCommand()),
    /**
     * The Edit password by admin.
     */
    EDIT_PASSWORD_BY_ADMIN(new EditPasswordByAdminCommand()),
    /**
     * The Find all clients by trainer.
     */
    FIND_ALL_CLIENTS_BY_TRAINER(new FindAllClientsByTrainerCommand()),
    /**
     * The Verification.
     */
    VERIFICATION(new VerificationCommand()),
    /**
     * The Load profile image by admin.
     */
    LOAD_PROFILE_IMAGE_BY_ADMIN(new LoadProfileImageByAdminCommand()),
    /**
     * The Load profile image by trainer.
     */
    LOAD_PROFILE_IMAGE_BY_TRAINER(new LoadProfileImageByTrainerCommand()),
    /**
     * The Load profile image by client.
     */
    LOAD_PROFILE_IMAGE_BY_CLIENT(new LoadProfileImageByClientCommand()),
    /**
     * The Change locale.
     */
    CHANGE_LOCALE(new LocaleCommand());

    CommandType(Command command) {
        this.command = command;
    }

    private Command command;

    /**
     * Gets command.
     *
     * @return the command
     */
    public Command getCommand() {
        return command;
    }
}
