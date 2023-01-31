public class TagError { // this class just makes a tag-error which holds two data members the line where the error happened and the tag name
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