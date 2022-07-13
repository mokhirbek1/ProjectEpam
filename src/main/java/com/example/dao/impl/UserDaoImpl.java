package com.example.demo11.dao.impl;

import com.example.demo11.dao.BaseDao;
import com.example.demo11.dao.UserDao;
import com.example.demo11.entity.User;
import com.example.demo11.exception.DaoException;
import com.example.demo11.pool.ConnectionPool;

import java.sql.*;
import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static final String SELECT_PASSWORD = "SELECT password FROM users WHERE lastname = ?";
    private static UserDaoImpl instance = new UserDaoImpl();
    private UserDaoImpl(){
    }
    public static UserDaoImpl getInstance(){
        return instance;
    }
    @Override
    public boolean insert(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public List<User> findAll(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean authenticate(String login, String password) throws DaoException {
        boolean match = false;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_PASSWORD)){
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            String passFromDB;
            if(resultSet.next()){
                passFromDB = resultSet.getString(1);
                match = password.equals(passFromDB);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return match;
    }
}
