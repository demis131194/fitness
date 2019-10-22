package by.epam.fitness.command.comands;

import by.epam.fitness.command.Command;
import by.epam.fitness.constants.PagePaths;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;

public class LogoutCommand implements Command {
    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {

        requestContent.putSessionAttribute("user", null);
        requestContent.putSessionAttribute("role", null);

        return PagePaths.INDEX_PATH;
    }
}
