package by.epam.fitness.command.impl;

import by.epam.fitness.command.Command;
import by.epam.fitness.controller.PagePath;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;

public class LogoutCommand implements Command {
    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {

        requestContent.invalidateSession();

        return PagePath.INDEX_PATH;
    }
}
