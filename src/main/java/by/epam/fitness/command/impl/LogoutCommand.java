package by.epam.fitness.command.impl;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;

public class LogoutCommand implements Command {
    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {

        requestContent.invalidateSession();
        String page = PagePath.INDEX_PATH;
        requestContent.putSessionAttribute(AttributeName.CURRENT_PAGE, page);
        return page;
    }
}
