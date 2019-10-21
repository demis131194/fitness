package by.epam.fitness.command.comands;

import by.epam.fitness.command.Command;
import by.epam.fitness.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public void execute(HttpServletRequest request) throws CommandException {

    }
}
