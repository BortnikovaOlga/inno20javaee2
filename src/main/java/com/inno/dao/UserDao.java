package com.inno.dao;

import com.inno.ConnectionManager.ConnectionManager;
import com.inno.pojo.User;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@EJB
public class UserDao {
    private ConnectionManager connectionManager;

    @Inject
    public UserDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public User getUser(String name, String password) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE login = ? and password = ?")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkUser(String name, String password) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE login = ? and password = ?")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return (resultSet.next()); }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
