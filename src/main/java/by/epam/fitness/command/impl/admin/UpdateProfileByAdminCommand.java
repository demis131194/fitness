package by.epam.fitness.command.impl.admin;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.ErrorMessageKey;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.controller.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.Admin;
import by.epam.fitness.service.AdminService;
import by.epam.fitness.service.impl.user.AdminServiceImpl;
import by.epam.fitness.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Update profile by admin command.
 */
public class UpdateProfileByAdminCommand implements Command {
    private static Logger logger = LogManager.getLogger(UpdateProfileByAdminCommand.class);

    private AdminService adminService = AdminServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        boolean isValidParameters = true;

        try {
            int clientId = Integer.parseInt(requestContent.getParameterByName(AttributeName.USER_ID));
            String userName = requestContent.getParameterByName(AttributeName.USER_NAME).strip();
            String userLastName = requestContent.getParameterByName(AttributeName.USER_LAST_NAME).strip();
            String userEmail = requestContent.getParameterByName(AttributeName.USER_MAIL).strip();


            if (!Validator.checkName(userName)) {
                isValidParameters = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_USER_NAME);
            }
            if (!Validator.checkLastName(userLastName) && isValidParameters) {
                isValidParameters = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_USER_LAST_NAME);
            }
            if (!Validator.checkEmail(userEmail) && isValidParameters) {
                isValidParameters = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_EMAIL);
            }

            if (isValidParameters) {
                Admin admin = new Admin();

                admin.setId(clientId);
                admin.setName(userName);
                admin.setLastName(userLastName);
                admin.setMail(userEmail);
                adminService.update(admin);

                admin = adminService.find(clientId);
                requestContent.putSessionAttribute(AttributeName.USER_NAME, admin.getName());
                requestContent.putSessionAttribute(AttributeName.USER_LAST_NAME, admin.getLastName());
                requestContent.putSessionAttribute(AttributeName.USER_MAIL, admin.getMail());
                page = PagePath.ADMIN_PROFILE_PATH;
            } else {
                page = PagePath.ADMIN_PROFILE_EDIT_PATH;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
