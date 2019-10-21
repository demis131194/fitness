package by.epam.fitness.command;

import by.epam.fitness.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    void execute(HttpServletRequest request) throws CommandException;
}
