public class Token {
    private String type;
    private String code;
    private String error;

    public Token(){

    }
    public Token(String type,String code){
        this.type=type;
        this.code=code;
        this.error="null";
    }
    public Token(String type,String code,String error){
        this.type="UNKNOWN";
        this.code=code;
        this.error=error;
    }
    @Override
    public String toString() {
        return "Token:{Type:"+type+",Code:"+code+",Error:"+error+"}";
    }
}
