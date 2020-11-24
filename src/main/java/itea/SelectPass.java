package itea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class SelectPass {
    public final static String SELECT_PASS = "SELECT cr.login, cr.password, u.name, u.nick_name, ug.group_name, ug.user_grants FROM credentials cr\n" +
            "JOIN users u ON cr.user_id = u.id JOIN user_group ug ON u.group_id = ug.id order by cr.id desc";

    public void selectPassword() {
        FileInputStream in = null;
        Properties props = new Properties();
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("Driver loaded!(to select)");
        } catch (Exception ex) {
            System.out.println("Error Driver loading!(to select)");
        }

        try {
            in = new FileInputStream("database.properties");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            props.load(in);
            System.out.println("Properties loaded!(to select)");
            in.close();
            System.out.println("FileInputStream closed!(to select)");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String url = props.getProperty("url");
            String username = props.getProperty("username2select");
            String password = props.getProperty("password2select");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected(to select)");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        try {
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(SELECT_PASS);
            while (rs.next()) {
                System.out.println(
                        "login: " + rs.getString("login") + "; password: " + rs.getString("password") + "; name: " +
                                rs.getString("name") + "; nick_name: " + rs.getString("nick_name") + "; group_name: " +
                                rs.getString("group_name") + "; user_grants: " + rs.getString("user_grants"));
            }
            rs.close();
            System.out.println("ResultSet closed!(to select)");
            st.close();
            System.out.println("Statement closed!(to select)");
            conn.close();
            System.out.println("Disconnected successfuly!(to select)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

