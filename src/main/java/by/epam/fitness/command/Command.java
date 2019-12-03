package by.epam.fitness.command;

import by.epam.fitness.controller.SessionRequestContent;
import by.epam.fitness.exception.CommandException;

/**
 * The interface Command.
 */
public interface Command {
    /**
     * Execute string.
     *
     * @param requestContent the request content
     * @return the string
     * @throws CommandException the command exception
     */
    String execute(SessionRequestContent requestContent) throws CommandException;
}
