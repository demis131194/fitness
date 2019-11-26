package by.epam.fitness.command.impl;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.ErrorMessageKey;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.Admin;
import by.epam.fitness.model.user.Client;
import by.epam.fitness.model.user.Trainer;
import by.epam.fitness.model.user.User;
import by.epam.fitness.service.AdminService;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.TrainerService;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.user.AdminServiceImpl;
import by.epam.fitness.service.impl.user.ClientServiceImpl;
import by.epam.fitness.service.impl.user.TrainerServiceImpl;
import by.epam.fitness.service.impl.user.UserServiceImpl;
import by.epam.fitness.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginCommand implements Command {
    private static Logger logger = LogManager.getLogger(LoginCommand.class);

    private static final String PARAM_NAME_LOGIN = "Login";
    private static final String PARAM_NAME_PASSWORD = "Password";

    private UserService userService = UserServiceImpl.getInstance();
    private AdminService adminService = AdminServiceImpl.getInstance();
    private TrainerService trainerService = TrainerServiceImpl.getInstance();
    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String login = requestContent.getParameterByName(PARAM_NAME_LOGIN);
        String password = requestContent.getParameterByName(PARAM_NAME_PASSWORD);

        User user;
        String page;
        try {
            if (Validator.checkLogin(login) && Validator.checkPassword(password)) {
                user = userService.findByLoginAndPassword(login, password);
                if (user != null) {
                    requestContent.putSessionAttribute(AttributeName.USER_ID, user.getId());
                    requestContent.putSessionAttribute(AttributeName.USER_PROFILE_IMG_PATH, user.getProfileImagePath());

                    switch (user.getRole()) {
                        case ADMIN:
                            Admin admin = adminService.find(user.getId());
                            requestContent.putSessionAttribute(AttributeName.USER_ROLE, admin.getRole().name());
                            requestContent.putSessionAttribute(AttributeName.USER_NAME, admin.getName());
                            requestContent.putSessionAttribute(AttributeName.USER_LAST_NAME, admin.getLastName());
                            requestContent.putSessionAttribute(AttributeName.USER_MAIL, admin.getMail());
                            page = PagePath.WELCOME_PATH;
                            break;
                        case TRAINER:
                            Trainer trainer = trainerService.find(user.getId());
                            requestContent.putSessionAttribute(AttributeName.USER_ROLE, trainer.getRole().name());
                            requestContent.putSessionAttribute(AttributeName.USER_NAME, trainer.getName());
                            requestContent.putSessionAttribute(AttributeName.USER_LAST_NAME, trainer.getLastName());
                            requestContent.putSessionAttribute(AttributeName.USER_REGISTER_DATE, trainer.getRegisterDateTime());
                            requestContent.putSessionAttribute(AttributeName.USER_PHONE, trainer.getPhone());
                            requestContent.putSessionAttribute(AttributeName.USER_MAIL, trainer.getMail());
                            page = PagePath.WELCOME_PATH;
                            break;
                        case CLIENT:
                            Client client = clientService.find(user.getId());
                            requestContent.putSessionAttribute(AttributeName.USER_ROLE, client.getRole().name());
                            requestContent.putSessionAttribute(AttributeName.USER_NAME, client.getName());
                            requestContent.putSessionAttribute(AttributeName.USER_LAST_NAME, client.getLastName());
                            requestContent.putSessionAttribute(AttributeName.USER_REGISTER_DATE, client.getRegisterDateTime());
                            requestContent.putSessionAttribute(AttributeName.USER_DISCOUNT, client.getDiscount());
                            requestContent.putSessionAttribute(AttributeName.USER_DISCOUNT_LEVEL, client.getDiscountLevel());
                            requestContent.putSessionAttribute(AttributeName.USER_PHONE, client.getPhone());
                            requestContent.putSessionAttribute(AttributeName.USER_CASH, client.getCash());
                            requestContent.putSessionAttribute(AttributeName.USER_MAIL, client.getMail());
                            page = PagePath.WELCOME_PATH;
                            break;
                        default:
                            throw new CommandException();
                    }
                    requestContent.putSessionAttribute(AttributeName.USER_AUTHORIZATION, true);
                } else {
                    page = (String) requestContent.getSessionAttributeByName(AttributeName.CURRENT_PAGE);
                    requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.WRONG_LOGIN_OR_PASSWORD);
                }

            } else {
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_LOGIN_OR_PASSWORD);
                page = (String) requestContent.getSessionAttributeByName(AttributeName.CURRENT_PAGE);
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
