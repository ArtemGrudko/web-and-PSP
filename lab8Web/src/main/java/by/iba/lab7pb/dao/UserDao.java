package by.iba.lab7pb.dao;

import by.iba.lab7pb.model.User;

import java.sql.*;

public class UserDao {

    private final static String SQL_GET_USER = "select * from users where login=? and passw=?";


    private final static String SQL_CHECK_LOGIN = "SELECT login FROM users WHERE login = ?";
    private final static String SQL_INSERT_USER = "INSERT INTO users(login ,passw) VALUES (? , ?)";
    private final static String SQL_GET_LAST = "SELECT * FROM users ORDER BY id DESC LIMIT 1";

    private Connection connection;


    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User isValidUser(final String login, final byte[] password) {

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_GET_USER);

            ps.setString(1, login);
            ps.setBytes(2, password);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return new User(rs.getString("login"), "xxx".getBytes(), rs.getBoolean("type"), rs.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public boolean insertUser(User user) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SQL_CHECK_LOGIN); preparedStatement.setString(1, user.getLogin()); ResultSet result = preparedStatement.executeQuery();
            if ( result.next()){ preparedStatement.close(); return false;
            }
            else {
                preparedStatement = connection.prepareStatement(SQL_INSERT_USER);

                preparedStatement.setString(1, user.getLogin()); preparedStatement.setBytes(2, user.getPassw());

                preparedStatement.executeUpdate(); preparedStatement.close();

            }
        } catch (SQLException e) { e.printStackTrace();
        }
        return true;
    }
    public int getLastUser(){
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_LAST);
            resultSet.next();
            return resultSet.getInt("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }



}
