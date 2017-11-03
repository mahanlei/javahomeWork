import java.sql.ResultSet;
import java.sql.SQLException;

public class Change {
    DBHelper dbHelper=new DBHelper();
    public void modification(){
        dbHelper.getConnection();
        String sql="UPDATE student SET accommodation=1200   WHERE dormitory=\"陶园1舍\"";
        try {
            dbHelper.stmt.executeUpdate(sql);
            dbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void changeDor(){
        dbHelper.getConnection();
        String female="";
        String male="";
        String sql1="SELECT DISTINCT dormitory FROM student WHERE department=\"软件学院\" AND gender=\"女\"";
        String sql2="SELECT DISTINCT dormitory FROM student WHERE department=\"软件学院\" AND gender=\"男\"";
        try {
            ResultSet resultSet1= dbHelper.stmt.executeQuery(sql1);

            while (resultSet1.next()){
               female=resultSet1.getString("dormitory");
            }
            ResultSet resultSet2=dbHelper.stmt.executeQuery(sql2);
            while (resultSet2.next()){
                male=resultSet2.getString("dormitory");
            }
            String sql3="update student set dormitory='"+male+"'where department=\"软件学院\" and gender=\"男\"";
            String sql4="update student set dormitory='"+female+"'where department=\"软件学院\" and gender=\"女\"";
            dbHelper.stmt.executeUpdate(sql3);
            dbHelper.stmt.executeUpdate(sql4);
            dbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("change successful!");
    }


}
