package by.epam.fitness.command.impl.trainer;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.controller.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.User;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.user.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Load profile image by trainer command.
 */
public class LoadProfileImageByTrainerCommand implements Command {
    private static Logger logger = LogManager.getLogger(LoadProfileImageByTrainerCommand.class);

    private static UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        int userId = (Integer) requestContent.getSessionAttributeByName(AttributeName.USER_ID);
        String imagePath = (String) requestContent.getAttributeByName(AttributeName.USER_PROFILE_IMG_PATH);

        String page;
        try {
            User user;
            if (imagePath != null) {
                user = new User();
                user.setId(userId);
                user.setProfileImagePath(imagePath);
                userService.updateUserProfileImg(user);

                user = userService.find(userId);
                requestContent.putSessionAttribute(AttributeName.USER_PROFILE_IMG_PATH, user.getProfileImagePath());
            }

            page = PagePath.TRAINER_PROFILE_PATH;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
