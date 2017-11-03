

import java.sql.*;


public class DBHelper {
    public static final String url = "jdbc:mysql://localhost:3306/stuDormitory?characterEncoding=utf8&useSSL=true";
    public static final String driver = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "leilei";

    public Connection conn = null;
    public Statement stmt = null;

    public void getConnection(){
        try {
            Class.forName(driver);
            conn=DriverManager.getConnection(url,user,password);
            stmt=conn.createStatement();
            System.out.println("connection successful！");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void close() {
        try {
            this.conn.close();
            this.stmt.close();
            System.out.println("close successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}