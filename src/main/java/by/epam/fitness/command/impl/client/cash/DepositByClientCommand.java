package by.epam.fitness.command.impl.client.cash;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.ErrorMessageKey;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.controller.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Card;
import by.epam.fitness.model.user.Client;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.impl.user.ClientServiceImpl;
import by.epam.fitness.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.MathContext;

public class DepositByClientCommand implements Command {
    private static Logger logger = LogManager.getLogger(DepositByClientCommand.class);

    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        boolean isValidParameters = true;
        try {
            int clientId = (Integer) requestContent.getSessionAttributeByName(AttributeName.USER_ID);
            String cashAmount = requestContent.getParameterByName(AttributeName.CASH_AMOUNT).strip();
            String cardNumber = requestContent.getParameterByName(AttributeName.CARD_NUMBER).strip();

            if (!Validator.checkCardNumber(cardNumber)) {
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INCORRECT_CARD_NUMBER);
                isValidParameters = false;
            }
            if (!Validator.checkCashAmount(cashAmount) && isValidParameters) {
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INCORRECT_CASH_AMOUNT);
                isValidParameters = false;
            }

            if (isValidParameters) {
                BigDecimal cash = new BigDecimal(requestContent.getParameterByName(AttributeName.CASH_AMOUNT), MathContext.DECIMAL32);

                Card card = new Card();
                card.setCardNumber(cardNumber);

                boolean isUpdated = clientService.depositCash(clientId, cash, card);
                if (isUpdated) {
                    requestContent.putAttribute(AttributeName.CASH_AMOUNT, cash.doubleValue());
                    Client client = clientService.find(clientId);
                    requestContent.putSessionAttribute(AttributeName.USER_CASH, client.getCash());
                    page = PagePath.CLIENT_DEPOSIT_SUCCESS_PATH;
                } else {
                    requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.CARD_NOT_EXISTS);
                    page = PagePath.CLIENT_DEPOSIT_PATH;
                }

            } else {
                page = PagePath.CLIENT_DEPOSIT_PATH;
            }

        } catch (ServiceException e) {
            requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.CARD_NOT_ENOUGH_AMOUNT);
            page = PagePath.CLIENT_DEPOSIT_PATH;
        }

        return page;
    }
}
