package by.epam.fitness.command.impl.client.comment;

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

public class CreateCommentCommand implements Command {
    private static Logger logger = LogManager.getLogger(CreateCommentCommand.class);

    private CommentService commentService = CommentServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            int clientId = (Integer) requestContent.getSessionAttributeByName(AttributeName.USER_ID);
            int trainerId = Integer.parseInt(requestContent.getParameterByName(AttributeName.TRAINER_ID));
            String clientComment = requestContent.getParameterByName(AttributeName.COMMENT).strip().replaceAll("<script>", "").replaceAll("</script>", "");;

            Comment comment = new Comment();
            comment.setClientId(clientId);
            comment.setTrainerId(trainerId);
            comment.setComment(clientComment);

            commentService.create(comment);
            page = PagePath.CLIENT_COMMENT_CREATED;

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
