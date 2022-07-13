package com.example.demo11.command.impl;

import com.example.demo11.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class AddUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return request.getParameter("command");
    }
}
