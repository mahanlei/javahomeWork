import java.util.List;

public class Test {
    public static void main(String [] args){
//       CreatTable creatTable=new CreatTable();
//       creatTable.creat();

        ReadFile readFile=new ReadFile();
//      List<List<String >> userRecord= readFile.readFromTxt("/Users/apple/Desktop/user.txt");
//      List<List<String >> bikeRecord= readFile.readFromTxt("/Users/apple/Desktop/bike.txt");
        List<List<String >> recordRecord= readFile.readFromTxt("/Users/apple/Desktop/record.txt");
        InsertData insertData=new InsertData();
//        insertData.insertUser(userRecord);
        insertData.insertRecord(recordRecord);
//        String date1="2017/10/01-22:21:00";
//        String date2="2017/10/01-22:27:00";
//        System.out.println(insertData.count(date1,date2));
    }
}
