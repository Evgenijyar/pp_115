package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement();) {
            statement.executeUpdate("CREATE TABLE Users (Id BIGINT AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(20), LastName VARCHAR(20), Age TINYINT);");
        } catch (SQLException e) {
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("DROP TABLE Users");
        } catch (SQLException e) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT Users(Name, LastName, Age) VALUES (?, ?, ?);";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM Users WHERE Id=?);";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement statement = Util.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");) {
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("Id"));
                user.setName(resultSet.getString("Name"));
                user.setLastName(resultSet.getString("LastName"));
                user.setAge(resultSet.getByte("Age"));
                userList.add(user);
            }
        } catch (SQLException e) {
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("DELETE FROM Users");
        } catch (SQLException e) {
        }
    }
}