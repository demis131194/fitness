package by.epam.fitness.command;

import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;

public interface Command {
    String execute(SessionRequestContent requestContent) throws CommandException;
}
