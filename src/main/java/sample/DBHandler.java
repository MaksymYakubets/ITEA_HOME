package sample;

import java.sql.*;


public class DBHandler {
    public final static String SELECT_PLAYER = "SELECT ID FROM PLAYERS WHERE NAME = ? AND PASSWORD = ?";
    public static final String URL = "jdbc:mysql://localhost/maximus?";
    public static final String USERNAME = "itea";
    public static final String PASSWORD = "lesson6";


    public DBHandler() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isPlayerExists(User user) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(SELECT_PLAYER)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());

            try (ResultSet resultSet = ps.executeQuery()) {
                return resultSet.first();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
