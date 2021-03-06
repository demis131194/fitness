package by.epam.fitness.controller;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.PagePath;
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

/**
 * The type File uploading servlet.
 */
@WebServlet(urlPatterns = {"/upload"})
@MultipartConfig(fileSizeThreshold = 1024 *  1024,
        maxFileSize = 1024 *  1024 *  5,
        maxRequestSize = 1024 *  1024 *  5 *  5)
public class FileUploadingServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(FileUploadingServlet.class);

    private static final String IMAGE_NAME_DIR = "image";
    private static final String USER_NAME_DIR = "user";
    private static final String UPLOAD_DIR = IMAGE_NAME_DIR + File.separator + USER_NAME_DIR;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String applicationDir = request.getServletContext().getRealPath("");
        int userId = (Integer) request.getSession().getAttribute(AttributeName.USER_ID);
        String uploadFileDir = applicationDir + UPLOAD_DIR + File.separator + userId + File.separator;
        File fileSaveDir = new File(uploadFileDir);
        if(!fileSaveDir.exists()){
            fileSaveDir.mkdirs();
        }
        String imagePath = null;

        for(Part part : request.getParts()) {
            if (part.getSubmittedFileName() != null && part.getContentType().contains("image")) {
                part.write(uploadFileDir + part.getSubmittedFileName());
                imagePath = request.getServletContext().getContextPath() + File.separator + UPLOAD_DIR + File.separator + userId + File.separator + part.getSubmittedFileName();
            }
        }
        request.setAttribute(AttributeName.USER_PROFILE_IMG_PATH, imagePath);
        request.getRequestDispatcher(PagePath.MAIN_CONTROLLER_PATH).forward(request, response);
    }
}
