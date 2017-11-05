import java.sql.SQLException;

public class Query {
    DBHelper dbHelper=new DBHelper();
    public void familyAddress(){
        dbHelper.getConnection();
        String sql="ALTER  TABLE  user add houseAddress varchar(255) ";
                String sql2="UPDATE user LEFT JOIN (\n" +
                        "  SELECT\n" +
                        "    uid,\n" +
                        "    endPlace,\n" +
                        "    count(*) AS max_time\n" +
                        "  FROM record\n" +
                        "  WHERE date_format(record.endTime, '%H') >= 18 && date_format(record.endTime, '%H') <= 23\n" +
                        "  GROUP BY uid, endPlace\n" +
                        "  HAVING (uid, max_time) = ANY (\n" +
                        "    SELECT\n" +
                        "      uid,\n" +
                        "      max(times) AS time\n" +
                        "    FROM (SELECT\n" +
                        "            r1.uid,\n" +
                        "            r1.endPlace,\n" +
                        "            count(*) AS times\n" +
                        "          FROM record r1\n" +
                        "          WHERE date_format(r1.endTime, '%H') >= 18 &&\n" +
                        "                date_format(r1.endTime, '%H') <= 23\n" +
                        "          GROUP BY r1.uid, r1.endPlace\n" +
                        "         ) AS all_places\n" +
                        "    GROUP BY uid\n" +
                        "  )\n" +
                        "  )as T ON user.uid=T.uid SET user.houseAddress=T.endPlace";
        try {
            dbHelper.stmt.executeUpdate(sql);
            dbHelper.stmt.executeUpdate(sql2);
            dbHelper.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void maintenance(){
        DBHelper dbHelper=new DBHelper();
        String sql1="INSERT maintenance (bid, last_address)\n" +
                "  SELECT\n" +
                "    T1.bid,\n" +
                "    T1.endPlace\n" +
                "  FROM record T1, (SELECT\n" +
                "                     bid,\n" +
                "                     max(endTime) lastTime\n" +
                "                   FROM record\n" +
                "                   GROUP BY bid) AS T2\n" +
                "  WHERE T1.bid IN (\n" +
                "    SELECT bid\n" +
                "    FROM record\n" +
                "    WHERE record.endTime >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH) AND record.endTime <= CURDATE()\n" +
                "    GROUP BY bid\n" +
                "    HAVING sum(timestampdiff(SECOND, record.startTime, record.endTime)) / 60 / 60 > 200\n" +
                "  ) AND T1.bid = T2.bid AND endTime = lastTime ";
        try {
            dbHelper.stmt.executeUpdate(sql1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
