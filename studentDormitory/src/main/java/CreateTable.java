import java.sql.SQLException;

public class CreateTable {

    DBHelper dbHelper=new DBHelper();
    public  void creat(){
        dbHelper.getConnection();

       String sql1="create table dormitory (dname varchar(100),phoneNumber varchar(100), PRIMARY KEY (dname)) CHARACTER SET = utf8";
       String sql2="create table student (sid varchar(50),sname varchar(50),gender varchar(10),department varchar(100)," +
               "                campus varchar(10),dormitory varchar(50),accommodation int(10)," +
               "                PRIMARY KEY (sid)) CHARACTER SET = utf8";
        try {
            dbHelper.stmt.executeUpdate(sql1);
            dbHelper.stmt.executeUpdate(sql2);
            dbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
