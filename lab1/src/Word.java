/*
save letters or numbers
 */
public class Word {
    private char[]value;
    private int index;
    public Word(){
        value=new char[100];
        index=0;
    }
    public void add(char c){
        value[index++]=c;
        value[index]='\0';
    }
    public char[] getValue() {
        return value;
    }
}
