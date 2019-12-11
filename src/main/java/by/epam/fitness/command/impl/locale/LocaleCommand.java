package by.epam.fitness.command.impl.locale;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.controller.SessionRequestContent;
import by.epam.fitness.exception.CommandException;

/**
 * The type Locale command.
 */
public class LocaleCommand implements Command {

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String locale = requestContent.getParameterByName(AttributeName.LOCALE);
        requestContent.putSessionAttribute(AttributeName.LOCALE, locale);
        String page = (String) requestContent.getSessionAttributeByName(AttributeName.CURRENT_PAGE);
        if (page == null) {
            page = PagePath.INDEX_PATH;
        }
        return page;
    }
}
