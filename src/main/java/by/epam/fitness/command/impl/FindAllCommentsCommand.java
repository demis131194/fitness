package by.epam.fitness.command.impl;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.service.CommentService;
import by.epam.fitness.service.impl.CommentServiceImpl;
import by.epam.fitness.to.CommentTo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindAllCommentsCommand implements Command {
    private static Logger logger = LogManager.getLogger(FindAllCommentsCommand.class);

    private CommentService commentService = CommentServiceImpl.getInstance();
    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        List<CommentTo> commentTos;
        try {
            commentTos = commentService.findAllActive();
            requestContent.putAttribute(AttributeName.COMMENTS, commentTos);
            page = PagePath.COMMENTS_PATH;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}