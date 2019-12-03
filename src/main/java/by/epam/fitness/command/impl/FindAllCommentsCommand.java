package by.epam.fitness.command.impl;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.controller.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Comment;
import by.epam.fitness.service.CommentService;
import by.epam.fitness.service.impl.CommentServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;


public class FindAllCommentsCommand implements Command {
    private static Logger logger = LogManager.getLogger(FindAllCommentsCommand.class);

    private CommentService commentService = CommentServiceImpl.getInstance();
    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        List<Comment> comments;
        try {
            int pageNumber = Integer.parseInt(requestContent.getParameterByName(AttributeName.PAGE));

            int numberOfComments = commentService.countAll(true);
            comments = commentService.findAllActiveLimit(pageNumber);

            int numberOfPages = new BigDecimal(numberOfComments).divide(new BigDecimal(5), MathContext.DECIMAL32).setScale(0, RoundingMode.UP).intValue();
            requestContent.putAttribute(AttributeName.NUMBER_OF_PAGES, numberOfPages);
            requestContent.putAttribute(AttributeName.CURRENT_NUMBER_PAGE, pageNumber);
            requestContent.putAttribute(AttributeName.COMMENTS, comments);
            page = PagePath.COMMENTS_PATH;

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
