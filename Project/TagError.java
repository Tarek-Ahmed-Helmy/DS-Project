public class TagError {
    int lineNumber;
    String content;
    TagError(String s){
        this.content = s;
    }
    TagError(int l, String s){
        this.lineNumber = l;
        this.content = s;
    }
}
