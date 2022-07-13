package com.example.demo11.command;

import com.example.demo11.exception.CommandException;
import com.example.demo11.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    String execute(HttpServletRequest request) throws CommandException;
    default void refresh(){};
}
