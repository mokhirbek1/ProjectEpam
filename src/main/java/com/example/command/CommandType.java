package com.example.demo11.command;

import com.example.demo11.command.impl.AddUserCommand;
import com.example.demo11.command.impl.DefaultCommand;
import com.example.demo11.command.impl.LoginCommand;
import com.example.demo11.command.impl.LogoutCommand;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Locale;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand());
    Command command;
    CommandType(Command command){
        this.command = command;
    }
    public static Command define(String commandStr){
        CommandType current = CommandType.valueOf(commandStr.toUpperCase());
        return current.command;
    }
}
