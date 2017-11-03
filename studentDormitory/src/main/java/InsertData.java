import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

public class InsertData {
    DBHelper db = new DBHelper();

    public void insertLot(List<List<String>> record) {
        String sid, sname, gender, department, campus, dormitory;
        int accommodation;

        try {
            db.getConnection();
            db.conn.setAutoCommit(false);
            for (int i = 0; i < record.size(); i++) {
                department = record.get(i).get(0);
                sid = record.get(i).get(1);
                sname = record.get(i).get(2);
                gender = record.get(i).get(3);
                campus = record.get(i).get(4);
                dormitory = record.get(i).get(5);
                accommodation = Integer.parseInt(record.get(i).get(6));

                String sql = "insert into student values ('"+sid+"','"+sname+"','"+gender+"','"+department+"','"+campus+"','"+dormitory+"','"+accommodation+"')";
//                System.out.println(sql);
                db.stmt.executeUpdate(sql);

            }
            db.conn.commit();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insert(List<List<String>> record){
        String dname,phoneNumber;
        db.getConnection();
        try {
            db.conn.setAutoCommit(false);
            for(int i=0;i<record.size();i++){
                dname=record.get(i).get(0);
                phoneNumber=record.get(i).get(1);

                String sql="insert into dormitory values('"+dname+"','"+phoneNumber+"')";
                db.stmt.executeUpdate(sql);
            }
            db.conn.commit();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
