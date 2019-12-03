package by.epam.fitness.command.impl.client;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.controller.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.impl.user.ClientServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Verification command.
 */
public class VerificationCommand implements Command {
    private static Logger logger = LogManager.getLogger(VerificationCommand.class);

    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        int clientId = Integer.parseInt(requestContent.getParameterByName(AttributeName.USER_ID));

        try {
            boolean isVerified = clientService.verification(clientId);

            if (isVerified) {
                requestContent.putAttribute(AttributeName.VERIFICATION, true);
            } else {
                requestContent.putAttribute(AttributeName.VERIFICATION, false);
            }
            page = PagePath.VERIFICATION_PATH;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
