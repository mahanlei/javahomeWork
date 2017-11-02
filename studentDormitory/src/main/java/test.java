import com.mysql.jdbc.Connection;

//import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class test {
    static String sql = null;
    static DBHelper db1 = null;
    static ResultSet ret = null;

    public static void main(String[] args) {
//        sql = "create table student （sid varchar(9),sname varchar(10),gender int(1),department varchar(100)," +
//                "campus varchar(10),dormitory varchar(50),accommodation int(5)" +
//                "PRIMARY KEY (sid)";//SQL语句
//        sql = "select sid,sname,gender from student";
//        db1 = new DBHelper(sql);//创建DBHelper对象
//
//        try {
//            ret = db1.pst.executeQuery();//执行语句，得到结果集
//            while (ret.next()) {
//                String sid = ret.getString(1);
//                String sname = ret.getString(2);
//                Integer gender = ret.getInt(3);
//                System.out.println(sid + "\t" + sname + "\t" + gender + "\t");
//            }//显示数据
//            ret.close();
//            db1.close();//关闭连接
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//      creatTable();
        ReadFile readFile=new ReadFile();
        List<List<String>> record=readFile.readFromxls("分配方案.xls");

    }

  private static   void creatTable(){
        sql="create table dormitory (dname varchar(100),phoneNumber varchar(100)) CHARACTER SET = utf8";
        db1=new DBHelper(sql);
        try {
            db1.pst.executeUpdate();
            db1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void insertRecord( List<List<String>> record){
      String sid,sname,gender,department,campus,dormitory;
      int accommodation;

      for(int i=0;i<record.size();i++){
                department=record.get(i).get(0);
                sid=record.get(i).get(1);
                sname=record.get(i).get(2);
                gender=record.get(i).get(3);
                campus=record.get(i).get(4);


      }

    }
}


