package com.example.demo11.command.impl;

import com.example.demo11.command.Command;
import com.example.demo11.exception.CommandException;
import com.example.demo11.exception.ServiceException;
import com.example.demo11.service.UserService;
import com.example.demo11.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserService userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        String page;
        try {
            if (userService.authenticate(login, password)){
                request.setAttribute("user", login);
                session.setAttribute("user_name", login);
                page = "pages/main.jsp";
            }
            else {
                page = "index.jsp";
                request.setAttribute("login_msg", "Incorrect login or password");
            }
            session.setAttribute("current_page", page);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
