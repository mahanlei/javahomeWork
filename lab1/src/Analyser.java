import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analyser {
    //reserved words
    private String[] reservedWords={"void","class","public",
            "private","protected","for","if","else","while","do",
            "int","double","char","boolean","String","new","try",
            "catch","static","return","this","main"};
    //    operators
    private String[] operators={"+","-","*","/",">","<","==",
            "=",">=","<=","+=","-=","*=","/=","&&","||","|",
            "&","!","!="};
    //    notes
    private String[] notes={"/*","*/"};
    //    punctuation
    private String[] punctuation={"{","}",";",".","(",")","[","]",":","\"",","};

    public static void main(String[] args){
        Analyser analyser=new Analyser();
        char[]codes=analyser.readTxt();

        analyser.writeTxt(analyser.scanner(codes));
    }
    //read code from .txt file
    private char[]readTxt(){
        char []input=new char[400];
        String inputFileName="/Users/apple/Desktop/javaProgram/lab1/Input";
        File file=new File(inputFileName);
        try {
            FileInputStream fileInputStream=new FileInputStream(file);
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String line;
            int i=0;
            while((line=bufferedReader.readLine())!=null){
                //消除空白行
                char[]temp=line.toCharArray();
                //消除空白字符
                for(char a:temp){
                    input[i++]=a;
                }
                input[i++]='\n';
            }
            input[i]='#';
            bufferedReader.close();
        } catch (FileNotFoundException e) {
           System.out.println("not found this file");
        } catch (IOException e) {
            System.out.println("IO Error");
        }
//        System.out.print("success");
        return input;
    }
    //write tokens to .txt file
    private void writeTxt(List<Token> tokens){
        File outputFile=new File("Output");
        if(!outputFile.exists()){
            try {
                outputFile.createNewFile();
                FileOutputStream fileOutputStream=new FileOutputStream(outputFile);
                OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);
                BufferedWriter bw=new BufferedWriter(outputStreamWriter);
                for(Token token:tokens){
                    bw.write(token.toString());
                    bw.newLine();
                }
            bw.flush();;
            bw.close();;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //analyze the code
    private List<Token> scanner(char[] codes) {
        List<Token> tokens = new ArrayList<>();
        int index = 0;
        Word word;
        while (codes[index] != '#') {
            word = new Word();
            ////begin with letter
            if (isLetter(codes[index])) {
                word.add(codes[index]);
                index++;
                while (isLetter(codes[index]) || isNumber(codes[index])) {
                    word.add(codes[index]);
                    index++;
                }
                String s = trans(word.getValue());
                //ReservedWord has higher priority
                if (isReservedWord(s)) {
                    tokens.add(new Token("reservedWord", s));
                } else {
                    tokens.add(new Token("ID", s));
                }
                //begin with number
            } else if (isNumber(codes[index])) {
                word.add(codes[index]);
                index++;

                //next is '.'
                if (ispoint(codes[index])) {
                    word.add(codes[index]);
                    index++;
                    if (!isNumber(codes[index])) {
                        Token token = new Token("", trans(word.getValue()), "Fail to Recognize");
                        tokens.add(token);
                    } else {
                        //next is number(float)
                        while (isNumber(codes[index])) {
                            word.add(codes[index]);
                            index++;
                        }
                        String s = trans(word.getValue());
                        tokens.add(new Token("Float", s));
                    }
                }
                //next is number
                else {
                    while (isNumber(codes[index])) {
                        word.add(codes[index]);
                        index++;
                    }
                    String s = trans(word.getValue());
                    tokens.add(new Token("Number", s));
                }
            }
            //begin with other character
            else {
                word.add(codes[index]);
                switch (codes[index]) {
                    case '+': {
                        index++;
                        if (codes[index] == '=') {
                            word.add(codes[index]);
                            index++;
                            Token token = new Token("Operator+=",trans(word.getValue()));
                            tokens.add(token);
                        } else {
                            Token token = new Token("Operator+", trans(word.getValue()));
                            tokens.add(token);
                        }
                        break;
                    }
                    case '-': {
                        index++;
                        if (isNumber(codes[index])) {
                            word.add(codes[index]);
                            index++;

                            //next is '.'
                            if (ispoint(codes[index])) {
                                word.add(codes[index]);
                                index++;
                                if (!isNumber(codes[index])) {
                                    Token token = new Token("",trans(word.getValue()), "Fail to Recognize");
                                    tokens.add(token);
                                } else {
                                    //next is number(float)
                                    //不知道是否需要添jia
                                    while (isNumber(codes[index])) {
                                        word.add(codes[index]);
                                        index++;
                                    }
                                    String s = trans(word.getValue());
                                    tokens.add(new Token("Negative Float", s));
                                }
                            }
                            //next is number
                            else {
                                while (isNumber(codes[index])) {
                                    word.add(codes[index]);
                                    index++;
                                }
                                String s = trans(word.getValue());
                                tokens.add(new Token("Negative Number", s));
                            }
                        } else {
                            if (codes[index] == '=') {
                                word.add(codes[index]);
                                index++;
                                Token token = new Token("Operator-=", trans(word.getValue()));
                                tokens.add(token);
                            } else {
                                Token token = new Token("Operator-", trans(word.getValue()));
                                tokens.add(token);
                            }
                        }
                        break;
                    }
                    case '*': {
                        index++;
                        if (codes[index] == '=') {
                            word.add(codes[index]);
                            index++;
                            Token token = new Token("Operator*=",trans(word.getValue()));
                            tokens.add(token);
                        } else {
                            Token token = new Token("Operator*", trans(word.getValue()));
                            tokens.add(token);
                        }
                        break;
                    }
                    case '=': {
                        index++;
                        if (codes[index] == '=') {
                            word.add(codes[index]);
                            index++;
                            Token token = new Token("Operator==",trans(word.getValue()));
                            tokens.add(token);
                        } else {
                            Token token = new Token("Operator=", trans(word.getValue()));
                            tokens.add(token);
                        }
                        break;
                    }
                    case '>': {
                        index++;
                        if (codes[index] == '=') {
                            word.add(codes[index]);
                            index++;
                            Token token = new Token("Operator>=", trans(word.getValue()));
                            tokens.add(token);
                        } else {
                            Token token = new Token("Operator=", trans(word.getValue()));
                            tokens.add(token);
                        }
                        break;
                    }
                    case '<': {
                        index++;
                        if (codes[index] == '=') {
                            word.add(codes[index]);
                            index++;
                            Token token = new Token("Operator<=", trans(word.getValue()));
                            tokens.add(token);
                        } else {
                            Token token = new Token("Operator=", trans(word.getValue()));
                            tokens.add(token);
                        }
                        break;
                    }
                    case '!': {
                        index++;
                        if (codes[index] == '=') {
                            word.add(codes[index]);
                            index++;
                            Token token = new Token("Operator!=", trans(word.getValue()));
                            tokens.add(token);
                        } else {
                            Token token = new Token("Operator=", trans(word.getValue()));
                            tokens.add(token);
                        }
                        break;
                    }
                    case '&': {
                        index++;
                        if (codes[index] == '&') {
                            word.add(codes[index]);
                            index++;
                            Token token = new Token("Operator&&", trans(word.getValue()));
                            tokens.add(token);
                        } else {
                            Token token = new Token("Operator&", trans(word.getValue()));
                            tokens.add(token);
                        }
                        break;
                    }
                    case '|': {
                        index++;
                        if (codes[index] == '|') {
                            word.add(codes[index]);
                            index++;
                            Token token = new Token("Operator||", trans(word.getValue()));
                            tokens.add(token);
                        } else {
                            Token token = new Token("Operator|", trans(word.getValue()));
                            tokens.add(token);
                        }
                        break;
                    }
                    case '/': {
                        index++;
                        //next is '*' (notes)
                        if (codes[index] == '*') {
                            word.add(codes[index]);
                            index++;
                            boolean find = false;
                            do {
                                if ((codes[index] != '\n') && (codes[index] != '*')) {
                                    word.add(codes[index]);
                                    index++;
                                } else {
                                    if (codes[index] == '\n') {
                                        //can only handle notes in a single line
                                        index++;
                                        Token token = new Token("", trans(word.getValue()), "Fail to Recognize!");
                                        tokens.add(token);
                                        break;
                                    } else {
                                        word.add(codes[index]);
                                        index++;
                                        //if next is '/', found it, else continue
                                        if (codes[index] == '/') {
                                            word.add(codes[index]);
                                            Token token = new Token("Notes", trans(word.getValue()));
                                            tokens.add(token);
                                            find = true;
                                            index++;
                                        }
                                    }
                                }
                            } while (!find);
                        }
                        if (codes[index] == '=') {
                            word.add(codes[index]);
                            index++;
                            Token token = new Token("Operator/=", trans(word.getValue()));
                            tokens.add(token);
                        } else {
                            Token token = new Token("Operator/", trans(word.getValue()));
                            tokens.add(token);
                        }
                        break;
                    }
                    case '\"': {
                        index++;
                        boolean find = false;
                        while (!find) {
                            if (codes[index] != '\n') {
                                word.add(codes[index]);
                                if (codes[index] == '\"') {
                                    find = true;
                                    Token token = new Token("Punctuation\"\"", trans(word.getValue()));
                                    tokens.add(token);
                                } else {
                                    index++;
                                }
                            } else {
                                index++;
                                Token token = new Token("",trans(word.getValue()), "Fail to Recognize!");
                                tokens.add(token);
                                break;
                            }
                        }
                        break;
                    }
                    case ':': {
                        index++;
                        Token token = new Token("Punctuation:", trans(word.getValue()));
                        tokens.add(token);
                        break;
                    }
                    case '{': {
                        index++;
                        Token token = new Token("Punctuation{", trans(word.getValue()));
                        tokens.add(token);
                        break;
                    }
                    case '}': {
                        index++;
                        Token token = new Token("Punctuation}", trans(word.getValue()));
                        tokens.add(token);
                        break;
                    }
                    case '[': {
                        index++;
                        Token token = new Token("Punctuation[", trans(word.getValue()));
                        tokens.add(token);
                        break;
                    }
                    case ']': {
                        index++;
                        Token token = new Token("Punctuation]", trans(word.getValue()));
                        tokens.add(token);
                        break;
                    }
                    case '(': {
                        index++;
                        Token token = new Token("Punctuation(", trans(word.getValue()));
                        tokens.add(token);
                        break;
                    }
                    case ')': {
                        index++;
                        Token token = new Token("Punctuation)", trans(word.getValue()));
                        tokens.add(token);
                        break;
                    }
                    case '.': {
                        index++;
                        Token token = new Token("Punctuation.", trans(word.getValue()));
                        tokens.add(token);
                        break;
                    }
                    case ',': {
                        index++;
                        Token token = new Token("Punctuation,", trans(word.getValue()));
                        tokens.add(token);
                        break;
                    }
                    default: {
                        index++;
                        break;
                    }
                }
            }

        }
        return tokens;
    }
    private boolean isLetter(char c){
        if(c>='a'&&c<='z'||c>='A'&&c<='Z'){
            return true;
        }
        else return false;
    }
    private boolean isNumber(char c){
        if(c>='0'&&c<='9'){
            return true;
        }
        else return false;
    }
    private boolean isReservedWord(String  c){
        for (String item:reservedWords){
            if(item.equals(c))
                return true;
        }
        return false;
    }
    private boolean ispoint(char c){
       return c=='.';
    }
    private String trans(char[] ch){
        String s;
        int i=0;
        while(ch[i]!='\0'){
            i++;
        }
        i=i-1;
        s=String.valueOf(ch).substring(0,i+1);
        return s;
    }
}
