package by.epam.fitness.controller;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.CommandType;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.model.FitnessCard;
import by.epam.fitness.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Main controller.
 */
@WebServlet(value = "/controller")
public class MainController extends HttpServlet {
    private static Logger logger = LogManager.getLogger(MainController.class);

    @Override
    public void init() throws ServletException {
        super.init();
        ConnectionPool.initPool();
        FitnessCard.init();
        if (FitnessCard.FITNESS_CARD.getCardNumber() == null) {
            throw new ServletException("Fitness card missing!");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionRequestContent content = new SessionRequestContent(req);
        Command command = CommandType.valueOf(req.getParameter(AttributeName.COMMAND).toUpperCase()).getCommand();
        String page = null;
        try {
            page = command.execute(content);
            content.insertAttributes(req);
            req.getRequestDispatcher(page).forward(req, resp);
        } catch (CommandException e) {
            logger.error(e);
            req.setAttribute(AttributeName.ERROR, e);
            req.getRequestDispatcher(PagePath.ERROR_PATH).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.getInstance().closeAllConnections();
    }
}
