package itea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class InsertPass {
    public final static String INSERT_PASSWORD = "INSERT INTO credentials (user_id, login, password) VALUES (?,?,?)";

    public void InsertPassword(int user_id, String login, String password) {
        FileInputStream in = null;
        Properties props = new Properties();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("Driver loaded!(to insert)");
        } catch (Exception ex) {
            System.out.println("Error Driver loading!(to insert)");
        }

        Connection conn = null;

        try {
            in = new FileInputStream("database.properties");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            props.load(in);
            System.out.println("Properties loaded!(to insert)");
            in.close();
            System.out.println("FileInputStream closed!(to insert)");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String url = props.getProperty("url");
            String username2 = props.getProperty("username2insert");
            String password2 = props.getProperty("password2insert");
            conn = DriverManager.getConnection(url, username2, password2);
            System.out.println("Connected(to insert)");


        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        try {
            PreparedStatement pst = conn.prepareStatement(INSERT_PASSWORD);
            pst.setInt(1, user_id);
            pst.setString(2, login);
            pst.setString(3, password);
            pst.execute();
            pst.close();
            System.out.println("PreparedStatement closed!(to insert)");
            conn.close();
            System.out.println("New password inserted successfuly!(to insert)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
