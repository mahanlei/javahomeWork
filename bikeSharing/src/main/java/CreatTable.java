import java.sql.SQLException;

public class CreatTable {
    DBHelper dbHelper = new DBHelper();

    public void creat() {
        dbHelper.getConnection();
//        String sql1 = "create table bike(bid int)";
//        String sql2 = "create table user(uid int,uname varchar(50),phone varchar(50),balance DOUBLE," +
//                "PRIMARY KEY (uid)) CHARACTER SET = utf8";
        String sql3 = "create table record(rid int auto_increment,uid int,bid int,startPlace varchar(255),startTime DATETIME," +
                "endPlace varchar(255),endTime DATETIME,cost double,PRIMARY KEY (rid))CHARACTER SET = utf8";
        try {
//            dbHelper.conn.setAutoCommit(false);

//            dbHelper.stmt.executeUpdate(sql1);
//            dbHelper.stmt.executeUpdate(sql2);
            dbHelper.stmt.executeUpdate(sql3);
//            dbHelper.conn.commit();
            dbHelper.close();
        } catch (SQLException e) {
//            try {
//                dbHelper.conn.rollback();
//            } catch (SQLException e1) {
                e.printStackTrace();
//            }
        }
    }
}
