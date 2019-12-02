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

public class RestoreCommentByAdmin implements Command {
    private static Logger logger = LogManager.getLogger(RestoreCommentByAdmin.class);

    private CommentService commentService = CommentServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        int commentId = Integer.parseInt(requestContent.getParameterByName(AttributeName.COMMENT_ID));

        try {
            Comment comment = new Comment();
            comment.setId(commentId);
            comment.setActive(true);
            commentService.update(comment);
            requestContent.putAttribute(AttributeName.COMMENT_ID, commentId);
            page = PagePath.ADMIN_COMMENT_RESTORED_PATH;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
