import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    class tagError {
        int lineNumber;
        String content;

        tagError(String s){
            this.content = s;
        }
        tagError(int l, String s){
            this.lineNumber = l;
            this.content = s;
        }
    }

    public void solveError(BufferedReader br) throws IOException {
        Pattern opening_tag= Pattern.compile("<(\\w+)>"), opening_tag_W= Pattern.compile("<(\\w+)>\\w+$"), closing_tag= Pattern.compile("</(\\w+)>");
        Matcher matcher_opening_tag, matcher_opening_tag_W, matcher_closing_tag;
        Stack<tagError> stack = new Stack<tagError>();
        String line=br.readLine();
        while(line!=null){
            matcher_opening_tag=opening_tag.matcher(line);
            matcher_opening_tag_W=opening_tag_W.matcher(line);
            matcher_closing_tag=closing_tag.matcher(line);
            boolean open=matcher_opening_tag.find(), open_W=matcher_opening_tag_W.find(), close=matcher_closing_tag.find();
            if(open){
                tagError error = new tagError(matcher_opening_tag.group(1));
                stack.push(error);
            }
            if(open_W && !close){
                //inline error (missing closing tag)
                stack.pop();
            }
            else if(open && close){
                if(matcher_opening_tag.group(1).equals(matcher_closing_tag.group(1))){
                    stack.pop();
                }
                else{ // like <name>2</id>
                    //inline error (wrong closing tag)
                    stack.pop();
                }
            }
            else if(close) {
                while(!(stack.peek().content.equals(matcher_closing_tag.group(1))&&!stack.isEmpty())) {
                    //line error to be solved (missing closing tag)
                    //top of the stake
                    stack.pop();
                }
                if(!stack.isEmpty())
                    stack.pop();
            }
            line=br.readLine();
        }
        while (!stack.isEmpty()){
            //line error to be solved (missing closing tag)
            //top of the stake
            stack.pop();
        }
    }

    public static boolean consistency(BufferedReader br) throws IOException {
        Pattern opening_tag= Pattern.compile("<(\\w+)>"), closing_tag= Pattern.compile("</(\\w+)>");
        Matcher matcher_opening_tag, matcher_closing_tag;
        Stack<String> stack = new Stack<String>();
        String line=br.readLine();
        while(line!=null){
            matcher_opening_tag=opening_tag.matcher(line);
            matcher_closing_tag=closing_tag.matcher(line);
            if(matcher_opening_tag.find()){
                stack.push(matcher_opening_tag.group(1));
            }
            if(matcher_closing_tag.find()) {
                if ((stack.peek().equals(matcher_closing_tag.group(1)))) {
                    stack.pop();
                }
                else return false;
            }
            line=br.readLine();
        }
        if(!stack.isEmpty()){
            return false;
        }
        return true;
    }

    public void showError(BufferedReader br) throws IOException {
        int lineNumber=1;
        Pattern opening_tag= Pattern.compile("<(\\w+)>"), closing_tag= Pattern.compile("</(\\w+)>");
        Matcher matcher_opening_tag, matcher_closing_tag;
        Stack<tagError> stack = new Stack<tagError>();
        String line=br.readLine();
        while(line!=null){
            matcher_opening_tag=opening_tag.matcher(line);
            matcher_closing_tag=closing_tag.matcher(line);
            boolean open=matcher_opening_tag.find(),close=matcher_closing_tag.find();
            if(open){
                tagError error = new tagError(lineNumber,matcher_opening_tag.group(1));
                stack.push(error);
            }
            if(open&&close){
                if(matcher_opening_tag.group(1).equals(matcher_closing_tag.group(1))){
                    stack.pop();
                }
                else{ // like <name>2</id>
                    System.out.println("error in line: " +stack.peek().lineNumber);
                    System.out.println("Missing closing tag </"+stack.peek().content+">" );
                    stack.pop();
                    System.out.println("error in line: "+lineNumber);
                    System.out.println("There is an additional closing tag: </"+matcher_closing_tag.group(1)+">");
                }
            }
            else if(close) {
                while(!(stack.peek().content.equals(matcher_closing_tag.group(1))&&!stack.isEmpty())) {
                    System.out.println("error in line: " +stack.peek().lineNumber);
                    System.out.println("Missing closing tag </"+stack.peek().content+">" );
                    stack.pop();
                }
                if(!stack.isEmpty())
                    stack.pop();
            }
            line=br.readLine();
            lineNumber++;
        }
        while (!stack.isEmpty()){
            System.out.println("error in line: " + stack.peek().lineNumber);
            System.out.println("Missing closing tag </"+stack.peek().content+">" );
            stack.pop();
        }
    }

    public static void main(String []args)throws Exception{
        FileReader fr=new FileReader("D:\\edu\\CSE331, Data Structures and Algorithms\\project\\sample-input.xml");
        BufferedReader br=new BufferedReader(fr);
        Main x = new Main();
        //if(consistency(br)) System.out.println("100%");
        // else System.out.println("eldonia msh tmam");
        x.showError(br);
    }
}
