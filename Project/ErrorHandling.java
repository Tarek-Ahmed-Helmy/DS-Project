import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// this class consists of 3 static methods one to solve the error, one to show the consistency and one to show the errors (if any)

public class ErrorHandling {  

    // solve the error by creating a pattern by regex and see if there is a match to it. if there was a match then go get that tag content and put it on a stack. 
    // if you find the closing tag of it then pop from stack and all things are fine. if you find a mismatch then there is something wrong.
    public static void solveError(BufferedReader br, BufferedWriter bw) throws IOException {
        Pattern opening_tag= Pattern.compile("<(\\w+)>"), opening_tag_W= Pattern.compile("<(\\w+)>\\w+"), closing_tag= Pattern.compile("</(\\w+)>");
        Matcher matcher_opening_tag, matcher_opening_tag_W, matcher_closing_tag;
        Stack<TagError> stack = new Stack<>();
        String line=br.readLine();
        while(line!=null){
            matcher_opening_tag=opening_tag.matcher(line);
            matcher_opening_tag_W=opening_tag_W.matcher(line);
            matcher_closing_tag=closing_tag.matcher(line);
            boolean open=matcher_opening_tag.find(), open_W=matcher_opening_tag_W.find(), close=matcher_closing_tag.find();
            boolean checkIfNoErrors=true;
            if(open){ // you found an opening tag then push to stack the possible error.
                TagError error = new TagError(matcher_opening_tag.group(1));
                stack.push(error);
            }
            if(open_W && !close){ // if there is an opening tag followed by some words like "<id>1 "and not a closing tag
                bw.append(line).append("</").append(matcher_opening_tag.group(1)).append(">\n");
                checkIfNoErrors=false;
                stack.pop();
            }
            else if(open && close){ // if there is an opening tag and a closing tag and thats the right match then pop from stack.
                if(matcher_opening_tag.group(1).equals(matcher_closing_tag.group(1))){
                    stack.pop();
                }
                else{
                    String line2=line.replaceAll(closing_tag.toString(), "</"+matcher_opening_tag.group(1)+">");
                    bw.append(line2).append(String.valueOf('\n'));
                    checkIfNoErrors=false;
                    stack.pop();
                }
            }
            else if(close) { // if there is only a close tag like "</user>" 
                while(!(stack.peek().content.equals(matcher_closing_tag.group(1))&&!stack.isEmpty())) {
                    bw.append("</").append(stack.peek().content).append(">\n");
                    stack.pop();
                }
                if(!stack.isEmpty())
                    stack.pop();
            }
            if(checkIfNoErrors)
                bw.append(line).append(String.valueOf('\n'));
            line=br.readLine();
        }
        while (!stack.isEmpty()){
            bw.append("</").append(stack.peek().content).append(">\n");
            stack.pop();
        }
    }

    public static boolean consistency(BufferedReader br) throws IOException {
        Pattern opening_tag= Pattern.compile("<(\\w+)>"), closing_tag= Pattern.compile("</(\\w+)>");
        Matcher matcher_opening_tag, matcher_closing_tag;
        Stack<String> stack = new Stack<>();
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
        return stack.isEmpty();
    }

    public static void showError(BufferedReader br) throws IOException {
        int lineNumber=1;
        Pattern opening_tag= Pattern.compile("<(\\w+)>"), opening_tag_W= Pattern.compile("<(\\w+)>\\w+"), closing_tag= Pattern.compile("</(\\w+)>");
        Matcher matcher_opening_tag, matcher_opening_tag_W, matcher_closing_tag;
        Stack<TagError> stack = new Stack<>();
        String line=br.readLine();
        while(line!=null){
            matcher_opening_tag=opening_tag.matcher(line);
            matcher_opening_tag_W=opening_tag_W.matcher(line);
            matcher_closing_tag=closing_tag.matcher(line);
            boolean open=matcher_opening_tag.find(), open_W=matcher_opening_tag_W.find(), close=matcher_closing_tag.find();
            if(open){
                TagError error = new TagError(lineNumber,matcher_opening_tag.group(1));
                stack.push(error);
            }
            if(open_W && !close){
                System.out.println("error in line: " +stack.peek().lineNumber);
                System.out.println("Missing closing tag </"+stack.peek().content+">" );
                stack.pop();
            }
            else if(open && close){
                if(matcher_opening_tag.group(1).equals(matcher_closing_tag.group(1))){
                    stack.pop();
                }
                else{
                    System.out.println("error in line: " +stack.peek().lineNumber);
                    System.out.println("Wrong closing tag </"+matcher_closing_tag.group(1)+">" );
                    stack.pop();
                }
            }
            else if(close) {
                int errorLineNumber = lineNumber;
                while(!(stack.peek().content.equals(matcher_closing_tag.group(1))&&!stack.isEmpty())) {
                    System.out.println("error in line: " + (errorLineNumber++-1));
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
            System.out.println("error in line: " + lineNumber++);
            System.out.println("Missing closing tag </"+stack.peek().content+">" );
            stack.pop();
        }
    }
}