package passwordsecurity2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    public String name;
    public String password;
    public String salt;


    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/upb4","root","");

        return conn;
    }

    public User(String name, String password, String salt) {
        this.name = name;
        this.password = password;
        this.salt = salt;
    }

    public static void create(User user) throws ClassNotFoundException, SQLException {

        Connection conn = getConnection();

        String query = "insert into users (name, password, salt) values(?, ?, ?);";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, user.name);
        ps.setString(2, user.password);
        ps.setString(3, user.salt);

        ps.execute();

        conn.close();
    }

    public static User find(String name) throws SQLException, ClassNotFoundException {
        List<User> users = all();

        for(User user : users) {
            if(user.name.equals(name)) {
                return user;
            }
        }

        return null;
    }

    public static List<User> all() throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();

        Statement stmt = conn.createStatement();
        ResultSet rs=stmt.executeQuery("select * from users");

        List<User> users = new ArrayList<>();

        while(rs.next()) {
            String name = rs.getString(2);
            String password = rs.getString(3);
            String salt = rs.getString(4);

            User user = new User(name, password, salt);
            users.add(user);
        }
        conn.close();

        return users;
    }
}
