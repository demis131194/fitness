package by.epam.fitness.command.impl;

import by.epam.fitness.command.Command;
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
            user = userService.findByLoginAndPassword(login, password);

            if (user != null) {
                requestContent.putSessionAttribute("authorization", true);
                requestContent.putSessionAttribute("userId", user.getId());

                switch (user.getRole()) {
                    case ADMIN:
                        Admin admin = adminService.find(user.getId());
                        requestContent.putSessionAttribute("userRole", admin.getRole().name());
                        requestContent.putSessionAttribute("userName", admin.getName());
                        requestContent.putSessionAttribute("userLastName", admin.getLastName());
                        page = PagePath.ADMIN_WELCOME_PATH;
                        break;
                    case TRAINER:
                        Trainer trainer = trainerService.find(user.getId());
                        requestContent.putSessionAttribute("userRole", trainer.getRole().name());
                        requestContent.putSessionAttribute("userName", trainer.getName());
                        requestContent.putSessionAttribute("userLastName", trainer.getLastName());
                        requestContent.putSessionAttribute("registerDateTime", trainer.getRegisterDateTime());
                        requestContent.putSessionAttribute("userPhone", trainer.getPhone());
                        page = PagePath.TRAINER_WELCOME_PATH;
                        break;
                    case CLIENT:
                        Client client = clientService.find(user.getId());
                        requestContent.putSessionAttribute("userRole", client.getRole().name());
                        requestContent.putSessionAttribute("userName", client.getName());
                        requestContent.putSessionAttribute("userLastName", client.getLastName());
                        requestContent.putSessionAttribute("registerDateTime", client.getRegisterDateTime());
                        requestContent.putSessionAttribute("userDiscount", client.getDiscount());
                        requestContent.putSessionAttribute("userDiscountLevel", client.getDiscountLevel());
                        requestContent.putSessionAttribute("userPhone", client.getPhone());
                        requestContent.putSessionAttribute("userCash", client.getCash());
                        page = PagePath.CLIENT_WELCOME_PATH;
                        break;
                    default:
                        throw new CommandException("User has not have init field role");
                }
            } else {
                page = PagePath.LOGIN_PATH;
                requestContent.putAttribute("wrongPassOrLogin", true);
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
