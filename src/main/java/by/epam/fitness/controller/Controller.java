package by.epam.fitness.controller;

import by.epam.fitness.command.Command;
import by.epam.fitness.command.CommandOperations;
import by.epam.fitness.constants.PagePaths;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.model.Order;
import by.epam.fitness.service.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        Command command = CommandOperations.valueOf(request.getParameter("command").toUpperCase()).getCommand();
        try {
            command.execute(request);
        } catch (CommandException e) {
            e.printStackTrace();
        }

    }


}
