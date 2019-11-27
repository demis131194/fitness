package by.epam.fitness.controller;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.User;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.user.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = {"/upload"})
@MultipartConfig(fileSizeThreshold = 1024 *  1024,
        maxFileSize = 1024 *  1024 *  5,
        maxRequestSize = 1024 *  1024 *  5 *  5)
public class FileUploadingServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(FileUploadingServlet.class);

    private static final String UPLOAD_DIR = "images/user";
    private static UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String applicationDir = request.getServletContext().getRealPath("");
        int userId = (Integer) request.getSession().getAttribute(AttributeName.USER_ID);
        String uploadFileDir = applicationDir + UPLOAD_DIR + File.separator + userId + File.separator;
        File fileSaveDir = new File(uploadFileDir);
        if(!fileSaveDir.exists()){
            fileSaveDir.mkdirs();
        }
        String imgPath = null;

        try {
            for(Part part : request.getParts()) {
                if (part.getSubmittedFileName() != null) {
                    part.write(uploadFileDir + part.getSubmittedFileName());
                    imgPath = request.getServletContext().getContextPath() + File.separator + UPLOAD_DIR + File.separator + userId + File.separator + part.getSubmittedFileName();
                }
            }

            User user;
            if (imgPath != null) {
                user = new User();
                user.setId(userId);
                user.setProfileImagePath(imgPath);
                userService.updateUserProfileImg(user);
            }

            user = userService.find(userId);
            request.getSession().setAttribute(AttributeName.USER_PROFILE_IMG_PATH, user.getProfileImagePath());

            String page;
            switch (user.getRole()) {
                case CLIENT:
                    page = PagePath.CLIENT_PROFILE_PATH;
                    break;
                case TRAINER:
                    page = PagePath.TRAINER_PROFILE_PATH;
                    break;
                case ADMIN:
                    page = PagePath.ADMIN_PROFILE_PATH;
                    break;
                default:
                    throw new RuntimeException();
            }

            request.getRequestDispatcher(page).forward(request, response);

        } catch (IOException | ServiceException e) {
            logger.warn(e);
        }

    }
}
