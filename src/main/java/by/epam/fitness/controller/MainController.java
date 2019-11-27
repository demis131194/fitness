package by.epam.fitness.controller;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.CommandType;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.pool.ConnectionPool;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class MainController extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ConnectionPool.initPool();
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
