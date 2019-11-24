package by.epam.fitness.command.impl.client;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.Client;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.impl.user.ClientServiceImpl;
import by.epam.fitness.util.ErrorMessageKey;
import by.epam.fitness.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateProfileByClientCommand implements Command {
    private static Logger logger = LogManager.getLogger(UpdateProfileByClientCommand.class);

    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        boolean isValidParameters = true;

        try {
            Integer clientId = Integer.parseInt(requestContent.getParameterByName(AttributeName.USER_ID));
            String userName = requestContent.getParameterByName(AttributeName.USER_NAME).strip();
            String userLastName = requestContent.getParameterByName(AttributeName.USER_LAST_NAME).strip();
            String userPhone = requestContent.getParameterByName(AttributeName.USER_PHONE).strip();
            String userEmail = requestContent.getParameterByName(AttributeName.USER_MAIL).strip();


            if (!Validator.checkName(userName)) {
                isValidParameters = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_USER_NAME);
            }
            if (!Validator.checkLastName(userLastName) && isValidParameters) {
                isValidParameters = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_USER_LAST_NAME);
            }
            if (!Validator.checkPhone(userPhone) && isValidParameters) {
                isValidParameters = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_USER_PHONE);
            }
            if (!Validator.checkEmail(userEmail) && isValidParameters) {
                isValidParameters = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_EMAIL);
            }


            if (isValidParameters) {
                Client client = new Client();

                client.setId(clientId);
                client.setName(userName);
                client.setLastName(userLastName);
                client.setPhone(userPhone);
                client.setMail(userEmail);
                clientService.update(client);

                client = clientService.find(clientId);
                requestContent.putSessionAttribute(AttributeName.USER_NAME, client.getName());
                requestContent.putSessionAttribute(AttributeName.USER_LAST_NAME, client.getLastName());
                requestContent.putSessionAttribute(AttributeName.USER_PHONE, client.getPhone());
                requestContent.putSessionAttribute(AttributeName.USER_MAIL, client.getMail());
                page = PagePath.CLIENT_PROFILE_PATH;
            } else {
                page = PagePath.CLIENT_PROFILE_EDIT_PATH;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
