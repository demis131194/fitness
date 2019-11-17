package by.epam.fitness.command.impl.admin.user;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.User;
import by.epam.fitness.model.user.UserRole;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.user.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteUserByAdminCommand implements Command {
    private static Logger logger = LogManager.getLogger(DeleteUserByAdminCommand.class);

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            int userId = Integer.parseInt(requestContent.getParameterByName(AttributeName.USER_ID));
            userService.delete(userId);
            page = PagePath.ADMIN_USER_UPDATED_PATH;

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
