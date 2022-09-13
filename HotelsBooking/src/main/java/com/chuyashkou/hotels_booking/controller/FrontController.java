package com.chuyashkou.hotels_booking.controller;

import com.chuyashkou.hotels_booking.command.Command;
import com.chuyashkou.hotels_booking.command.CommandFactory;
import com.chuyashkou.hotels_booking.command.CommandResult;
import com.chuyashkou.hotels_booking.connection.ConnectionPool;
import com.chuyashkou.hotels_booking.exception.CommandException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/frontController")
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.getInstance().closePool();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ConnectionPool.getInstance();
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter("command");
        Command command = CommandFactory.defineCommand(commandName);
        try {
            CommandResult result = command.execute(req);
            switch (result.getNavigationType()) {
                case FORWARD -> getServletContext().getRequestDispatcher(result.getPage()).forward(req, resp);
                case REDIRECT -> resp.sendRedirect(result.getPage());
                default -> throw new RuntimeException();
            }
        } catch (CommandException e) {
            throw new ServletException(ExceptionMessage.EXCEPTION_DURING_EXECUTE_COMMND, e);
        }
    }
}
