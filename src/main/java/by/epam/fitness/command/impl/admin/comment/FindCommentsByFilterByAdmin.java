package by.epam.fitness.command.impl.admin.comment;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Comment;
import by.epam.fitness.service.CommentService;
import by.epam.fitness.service.impl.CommentServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class FindCommentsByFilterByAdmin implements Command {
    private static Logger logger = LogManager.getLogger(FindCommentsByFilterByAdmin.class);

    private CommentService commentService = CommentServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            String trainerName = requestContent.getParameterByName(AttributeName.TRAINER_NAME);
            String trainerLastName = requestContent.getParameterByName(AttributeName.TRAINER_LAST_NAME);
            String clientName = requestContent.getParameterByName(AttributeName.CLIENT_NAME);
            String clientLastName = requestContent.getParameterByName(AttributeName.CLIENT_LAST_NAME);
            String regDate = requestContent.getParameterByName(AttributeName.REGISTER_DATE);
            String active = requestContent.getParameterByName(AttributeName.ACTIVE);

            //todo VALIDATION
            Comment filter = new Comment();
            if (clientName != null && !clientName.isBlank()) {
                filter.setClientName(clientName);
            }
            if (clientLastName != null && !clientLastName.isBlank()) {
                filter.setClientLastName(clientLastName);
            }
            if (trainerName != null && !trainerName.isBlank()) {
                filter.setTrainerName(trainerName);
            }
            if (trainerLastName != null && !trainerLastName.isBlank()) {
                filter.setTrainerLastName(trainerLastName);
            }
            if (regDate != null && !regDate.isBlank()) {
                filter.setRegisterDate(LocalDate.parse(regDate).atStartOfDay());
            }
            if (active != null && !active.isBlank()) {
                filter.setActive(Boolean.parseBoolean(active));
            } else {
                filter.setActive(null);
            }

            List<Comment> result = commentService.findAllByFilter(filter);

            requestContent.putAttribute(AttributeName.COMMENTS, result);
            page = PagePath.ADMIN_COMMENTS_PATH;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
