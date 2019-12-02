package by.epam.fitness.command.impl.admin;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.ErrorMessageKey;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.controller.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.User;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.user.UserServiceImpl;
import by.epam.fitness.util.PasswordEncoder;
import by.epam.fitness.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditPasswordByAdminCommand implements Command {
    private static Logger logger = LogManager.getLogger(EditPasswordByAdminCommand.class);

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        boolean isValidParameters = true;

        try {
            int adminId = Integer.parseInt(requestContent.getParameterByName(AttributeName.USER_ID));
            String currentPassword = requestContent.getParameterByName(AttributeName.CURRENT_PASSWORD).strip();
            String newPassword = requestContent.getParameterByName(AttributeName.NEW_PASSWORD).strip();
            String repeatPassword = requestContent.getParameterByName(AttributeName.REPEAT_PASSWORD).strip();


            if (!Validator.checkPassword(currentPassword)) {
                isValidParameters = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_CURRENT_PASSWORD);
            }
            if (!Validator.checkPassword(newPassword) && isValidParameters) {
                isValidParameters = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_NEW_PASSWORD);
            }
            if (!repeatPassword.equals(newPassword) && isValidParameters) {
                isValidParameters = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.PASSWORDS_NOT_EQUAL);
            }


            if (isValidParameters) {
                User user = userService.find(adminId);
                currentPassword = PasswordEncoder.encode(currentPassword);
                if (currentPassword.equals(user.getPassword())) {
                    user.setPassword(newPassword);
                    userService.updateUserPassword(user);
                    page = PagePath.ADMIN_SUCCESS_CHANGE_PASSWORD_PATH;

                } else {
                    requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_PASSWORD);
                    page = PagePath.ADMIN_EDIT_PASSWORD_PATH;
                }

            } else {
                page = PagePath.ADMIN_EDIT_PASSWORD_PATH;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
