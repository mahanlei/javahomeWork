import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
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
//                    System.out.print(s[i]+",");
                }
//                System.out.println("");
                aRecord.add(temp);
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aRecord;


    }
}
