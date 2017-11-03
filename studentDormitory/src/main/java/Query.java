import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {
    DBHelper dbHelper=new DBHelper();
    public void q4(){

        dbHelper.getConnection();
        String sql="SELECT DISTINCT s2.department FROM student s1,student s2 WHERE s1.sname=\"王小星\" AND s1.dormitory=s2.dormitory ";
        try {
            ResultSet resultSet=dbHelper.stmt.executeQuery(sql);
            while (resultSet.next()){
                String dname=resultSet.getString("department");

                System.out.println(dname);
            }
            dbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
