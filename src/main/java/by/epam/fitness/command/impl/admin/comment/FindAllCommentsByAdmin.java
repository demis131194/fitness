package by.epam.fitness.command.impl.admin.comment;

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

import java.util.List;

/**
 * The type Find all comments by admin.
 */
public class FindAllCommentsByAdmin implements Command {
    private static Logger logger = LogManager.getLogger(FindAllCommentsByAdmin.class);

    private CommentService commentService = CommentServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            List<Comment> comments = commentService.findAll();
            page = PagePath.ADMIN_COMMENTS_PATH;
            requestContent.putAttribute(AttributeName.COMMENTS, comments);

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
