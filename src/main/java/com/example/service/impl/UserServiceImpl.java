package com.example.demo11.service.impl;

import com.example.demo11.dao.impl.UserDaoImpl;
import com.example.demo11.exception.DaoException;
import com.example.demo11.exception.ServiceException;
import com.example.demo11.service.UserService;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = new UserServiceImpl();
    private UserServiceImpl(){
    }
    public  static UserServiceImpl getInstance(){
        return instance;
    }
    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean match = false;
        try {
            match = userDao.authenticate(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return match;
    }
}
