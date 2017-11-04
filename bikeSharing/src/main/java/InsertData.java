

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

public class InsertData {

    public void insertUser(List<List<String>> record){
        int uid;
        String uname,phone;
        double balance;
        DBHelper dbHelper=new DBHelper();
        dbHelper.getConnection();
        try {
            dbHelper.conn.setAutoCommit(false);

        for(int i=0;i<record.size();i++){
            uid=Integer.parseInt(record.get(i).get(0));
            uname=record.get(i).get(1);
            phone=record.get(i).get(2);
            balance=Double.parseDouble(record.get(i).get(3));

            String sql="insert into user values('"+uid+"','"+uname+"','"+phone+"','"+balance+"')";
            dbHelper.stmt.executeUpdate(sql);
        }
            dbHelper.conn.commit();
            dbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void insertbike(List<List<String>> record){
        int bid;

        DBHelper dbHelper=new DBHelper();
        dbHelper.getConnection();
        try {
            dbHelper.conn.setAutoCommit(false);

            for(int i=0;i<record.size();i++){
                bid=Integer.parseInt(record.get(i).get(0));


                String sql="insert into bike values('"+bid+"')";
                dbHelper.stmt.executeUpdate(sql);
            }
            dbHelper.conn.commit();
            dbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //插入record数据的同时更新user的数据
    public void insertRecord(List<List<String>> record){
        int rid=0,uid=0,bid=0;
        String startPlace="",endPlace="";
        String startTime="",endTime="";
        double cost=0.0;
        double balance=0.0;
        DBHelper dbHelper=new DBHelper();
        dbHelper.getConnection();
        try {
//             dbHelper.conn.setAutoCommit(false);

            for(int i=0;i<record.size();i++){
              String   uidSt=record.get(i).get(0);
              String bidSt=record.get(i).get(1);

                    startPlace=record.get(i).get(2);
                    startTime=record.get(i).get(3);
                    endPlace=record.get(i).get(4);
                    endTime=record.get(i).get(5);

                    uid=Integer.parseInt(uidSt);
                    bid=Integer.parseInt(bidSt);
                 double   time =count(startTime,endTime);
                    if(time<=0.5){
                        cost=1.0;
                    }else if(time>0.5&&time<1){
                        cost=2.0;
                    }else if(time>=1&&time<1.5){
                        cost=3.0;
                    }else if(time>=1.5){
                        cost=4.0;
                    }
                String sql0="select balance from user where uid='"+uid+"'";
//                System.out.println(sql0);
                ResultSet resultSet=dbHelper.stmt.executeQuery(sql0);
                while (resultSet.next()){
                    balance=resultSet.getDouble("balance");
                }
                System.out.println(balance);
                if(balance>0.0) {
                    balance=balance-cost;
                    System.out.println(balance);
                    String sql1 = "insert into record values(null,'" + uid + "','" + bid + "','" + startPlace
                            + "','" + startTime + "','" + endPlace + "','" + endTime + "','" + cost + "')";

//                    System.out.println(sql1);
                    dbHelper.stmt.executeUpdate(sql1);
                    String sql2 = "update user set balance='"+balance+"'WHERE uid='"+uid+"'";
//                    System.out.println(sql2);
//
                    dbHelper.stmt.executeUpdate(sql2);
                }
            }
//            dbHelper.conn.commit();


            dbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public double count(String dateString1,String dateString2){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");
        Date date1=new Date();
        Date date2=new Date();
        try {
           date1=format.parse(dateString1);
           date2=format.parse(dateString2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        double timeDelta=(date1.getTime()-date2.getTime())/1000;
        double secondsDelta=timeDelta>0?(int)timeDelta:(int)Math.abs(timeDelta);
        return secondsDelta/60/60;
    }
}
