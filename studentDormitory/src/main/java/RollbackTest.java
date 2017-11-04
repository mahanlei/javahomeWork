import java.sql.ResultSet;
import java.sql.SQLException;

public class RollbackTest {
    DBHelper dbHelper=new DBHelper();
public void rtest(){
dbHelper.getConnection();
    String d="12幢";
    String p="13585141983";

    try {

        String sql1="select phoneNumber from dormitory where dname='"+d+"'";
        ResultSet resultSet=dbHelper.stmt.executeQuery(sql1);
        while (resultSet.next()){
            p=resultSet.getString("phoneNumber");
            System.out.println(p);
        }
        if(p.equals("89680012")) {
            String sql2 = "update dormitory set phoneNumber='" + p + "' where dname='" + d + "'";
            System.out.println(sql2);
            dbHelper.stmt.executeUpdate(sql2);
        }

//        dbHelper.conn.rollback();

        dbHelper.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }

}
}
