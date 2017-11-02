import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    Workbook workbook;
    Sheet sheet;
    Cell cell0,cell1,cell2,cell3,cell4,cell5,cell6;

    public List<List<String>> readFromxls(String fileName){
        List<List<String>> aRecord=new ArrayList<List<String>>();
        try {
            workbook=Workbook.getWorkbook(new File(fileName));
            sheet=workbook.getSheet(0);
            int line=1;
            while (true) {
                cell0 = sheet.getCell(0,line);
                cell1 = sheet.getCell(1,line);
                cell2 = sheet.getCell(2,line);
                cell3 = sheet.getCell(3,line);
                cell4 = sheet.getCell(4,line);
                cell5 = sheet.getCell(5,line);
                cell6 = sheet.getCell(6,line);

                   List<String> temp=new ArrayList<String>();
                   temp.add(cell0.getContents());
                temp.add(cell1.getContents());
                temp.add(cell2.getContents());
                temp.add(cell3.getContents());
                temp.add(cell4.getContents());
                temp.add(cell5.getContents());
                temp.add(cell6.getContents());



                if("".equals(cell0.getContents())==true)    //如果读取的数据为空
                    break;
                line++;
                aRecord.add(temp);
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return aRecord;
    }
}
