package com.example.demo11.service;

import com.example.demo11.exception.ServiceException;

public interface UserService {
    boolean authenticate(String login, String password) throws ServiceException;
}
