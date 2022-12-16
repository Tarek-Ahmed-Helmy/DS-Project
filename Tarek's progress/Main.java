import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    class ET {
        int lineNumber;
        String content;
        ET(int l, String s){
            this.lineNumber = l;
            this.content = s;
        }
    }

    public static void printStack(Stack<ET> st) {
        while (!st.isEmpty()){
            System.out.println(st.peek().content);
            st.pop();
        }
        System.out.println(".............");
    }
    public static void solveError(){

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
        return true;
    }

    public void showError(BufferedReader br) throws IOException {
        int lineNumber=1;
        Pattern opening_tag= Pattern.compile("<(\\w+)>"), closing_tag= Pattern.compile("</(\\w+)>");
        Matcher matcher_opening_tag, matcher_closing_tag;
        Stack<ET> stack = new Stack<ET>();

        String line=br.readLine();
        while(line!=null){
            matcher_opening_tag=opening_tag.matcher(line);
            matcher_closing_tag=closing_tag.matcher(line);
            if(matcher_opening_tag.find()){
                ET error = new ET(lineNumber,matcher_opening_tag.group(1));
                stack.push(error);
            }
            if(matcher_closing_tag.find()) {
                if (!(stack.peek().content.equals(matcher_closing_tag.group(1)))) {
                    System.out.println("error in line: " + lineNumber);
                    System.out.println("Missing closing tag </"+stack.peek().content+">" );
                    stack.pop();
                    continue;
                }

                stack.pop();

            }
            line=br.readLine();
            lineNumber++;
        }
    }

    public static void main(String []args)throws Exception{
        FileReader fr=new FileReader("H:\\Data Structure Project CLone\\DS-Project\\sample-input.xml");
        BufferedReader br=new BufferedReader(fr);
        Main x = new Main();
        //if(consistency(br)) System.out.println("100%");
        //else System.out.println("eldonia msh tmam");
        x.showError(br);
    }
}