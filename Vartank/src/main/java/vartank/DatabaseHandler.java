package vartank;

import java.sql.*;

public class DatabaseHandler {
    public final static String SELECT_PLAYER = "SELECT ID FROM PLAYERS WHERE NAME = ? AND PASSWORD = ?";

    public Connection getDBConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
        }
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost/maximus?";
            String username = "itea";
            String password = "lesson6";
            conn = DriverManager.getConnection(url, username, password);

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conn;

    }

    public ResultSet getPlayer(User user) {
        ResultSet resultSet = null;

        try {
            PreparedStatement prSt = null;
            try {
                prSt = getDBConnection().prepareStatement(SELECT_PLAYER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            resultSet = prSt.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultSet;

    }

}

