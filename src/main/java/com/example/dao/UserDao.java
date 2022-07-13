package com.example.demo11.dao;

import com.example.demo11.exception.DaoException;

public interface UserDao {
    boolean authenticate(String login, String password) throws DaoException;
}
