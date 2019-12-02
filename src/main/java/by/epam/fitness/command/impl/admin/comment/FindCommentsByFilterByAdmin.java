package by.epam.fitness.command.impl.admin.comment;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.ErrorMessageKey;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.controller.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Comment;
import by.epam.fitness.service.CommentService;
import by.epam.fitness.service.impl.CommentServiceImpl;
import by.epam.fitness.util.Validator;
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
        boolean isValidParameters = true;
        try {
            String trainerName = requestContent.getParameterByName(AttributeName.TRAINER_NAME).strip();
            String trainerLastName = requestContent.getParameterByName(AttributeName.TRAINER_LAST_NAME).strip();
            String clientName = requestContent.getParameterByName(AttributeName.CLIENT_NAME).strip();
            String clientLastName = requestContent.getParameterByName(AttributeName.CLIENT_LAST_NAME).strip();
            String regDate = requestContent.getParameterByName(AttributeName.REGISTER_DATE);
            String active = requestContent.getParameterByName(AttributeName.ACTIVE);

            if (!Validator.checkName(trainerName)) {
                if (trainerName.isBlank()) {
                    trainerName = null;
                } else {
                    isValidParameters = false;
                    requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_TRAINER_NAME);
                }
            }
            if (!Validator.checkLastName(trainerLastName) && isValidParameters) {
                if (trainerLastName.isBlank()) {
                    trainerLastName = null;
                } else {
                    isValidParameters = false;
                    requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_TRAINER_LAST_NAME);
                }
            }
            if (!Validator.checkName(clientName) && isValidParameters) {
                if (clientName.isBlank()) {
                    clientName = null;
                } else {
                    isValidParameters = false;
                    requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_USER_NAME);
                }
            }
            if (!Validator.checkLastName(clientLastName) && isValidParameters) {
                if (clientLastName.isBlank()) {
                    clientLastName = null;
                } else {
                    isValidParameters = false;
                    requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_USER_LAST_NAME);
                }

            }

            if (isValidParameters) {
                Comment filter = new Comment();
                filter.setClientName(clientName);
                filter.setClientLastName(clientLastName);
                filter.setTrainerName(trainerName);
                filter.setTrainerLastName(trainerLastName);
                if (!regDate.isBlank()) {
                    filter.setRegisterDate(LocalDate.parse(regDate).atStartOfDay());
                } else {
                    filter.setRegisterDate(null);
                }
                if (!active.isBlank()) {
                    filter.setActive(Boolean.parseBoolean(active));
                } else {
                    filter.setActive(null);
                }

                List<Comment> result = commentService.findAllByFilter(filter);

                requestContent.putAttribute(AttributeName.COMMENTS, result);
                page = PagePath.ADMIN_COMMENTS_PATH;
            } else {
                page = PagePath.ADMIN_COMMENTS_PATH;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
