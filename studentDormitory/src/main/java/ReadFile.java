import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {


    public List<List<String>> readFromxls(String fileName){
        Workbook workbook;
        Sheet sheet;
        Cell cell0,cell1,cell2,cell3,cell4,cell5,cell6;
        List<List<String>> aRecord=new ArrayList<List<String>>();
        try {
            workbook=Workbook.getWorkbook(new File(fileName));
            sheet=workbook.getSheet(0);
            int line=1;
           int lineNum= sheet.getRows();
            while (true) {

                cell0 = sheet.getCell(0,line);


                cell1 = sheet.getCell(1,line);
                cell2 = sheet.getCell(2,line);
                cell3 = sheet.getCell(3,line);
                cell4 = sheet.getCell(4,line);
                cell5 = sheet.getCell(5,line);
                cell6 = sheet.getCell(6,line);
//                System.out.println(cell0.getContents()+","+cell1.getContents()+","+cell2.getContents()+","
//                        +cell3.getContents()+","+cell4.getContents()+","+cell5.getContents()+","+cell6.getContents());

                List<String> temp=new ArrayList<String>();
                temp.add(cell0.getContents());
                temp.add(cell1.getContents());
                temp.add(cell2.getContents());
                temp.add(cell3.getContents());
                temp.add(cell4.getContents());
                temp.add(cell5.getContents());
                temp.add(cell6.getContents());
                aRecord.add(temp);
                line++;
                if(line==lineNum) {  //如果读取的数据为空
                    break;
                }
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return aRecord;
    }
    public List<List<String>> readFromTxt(String fileName){
        List<List<String>> aRecord=new ArrayList<List<String>>();
        File file=new File(fileName);
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
            String line="";
            while ((line=bufferedReader.readLine())!=null){
                List<String> temp=new ArrayList<String>();
                String[] s=line.split(";");
                for(int i=0;i<s.length;i++) {
                    temp.add(s[i]);
//                    System.out.print(s[i]);
                }
//                System.out.println("/n");
                aRecord.add(temp);


            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        aRecord.remove(0);
        return aRecord;


    }

}
