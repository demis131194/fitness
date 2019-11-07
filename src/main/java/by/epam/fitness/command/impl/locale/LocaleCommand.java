package by.epam.fitness.command.impl.locale;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;

public class LocaleCommand implements Command {

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String locale = requestContent.getParameterByName(AttributeName.LOCALE);
        requestContent.putSessionAttribute(AttributeName.LOCALE, locale);
        String page = (String) requestContent.getSessionAttributeByName(AttributeName.CURRENT_PAGE);
        return page;
    }
}
