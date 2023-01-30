import java.io.*;
import java.util.*;
public class Compression {
    public static ArrayList<String> tokens = new ArrayList<String>();

    public static void setTokens(ArrayList<String> tokens) {
        Compression.tokens = tokens;
    }
    public static ArrayList<String> getTokens() {
        return tokens;
    }

    public static String compress(BufferedReader br, BufferedWriter bw) throws IOException{
        String line = br.readLine();
        StringBuffer XML = new StringBuffer(line);
        while(line != null){
            XML.append(line);
            XML.append("\n");
            line = br.readLine();
        }
        String s = XML.toString();
        StringBuilder sb = new StringBuilder("");
        String replace;
        int index;
        String[] chars = XML.toString().split("");
        for(int i = 0; i<chars.length ; i++){
            if(!tokens.contains(chars[i])){
                tokens.add(chars[i]);
            }
        }
        int i = 0;
        int j;
        while( i < s.length()){
            StringBuilder temp = new StringBuilder("");
            j = i;
//            if(s.charAt(j) == '\n') sb.append('\n');
            temp.append(s.charAt(j));
            j++;
            while(tokens.contains(temp.toString()) && j <s.length()){
                temp.append(s.charAt(j));
//                if(s.charAt(j) == '\n') sb.append('\n');
                j++;
            }
            if(tokens.contains(temp.toString())) {
                replace = temp.toString();
                i++;
            }
            else replace = temp.substring(0 , temp.length() - 1);
            if(!tokens.contains(temp.toString())) tokens.add(temp.toString());
            index = tokens.indexOf(replace);
            sb.append(index);
            sb.append(' ');
            if(i == s.length() - 1) break;
            i += temp.length() - 1;
        }
        bw.write(sb.toString());
        bw.close();
        return sb.toString();
    }
    public static String decompress(String s) throws IOException {
        /*String line = br.readLine();
        StringBuffer XML = new StringBuffer(line);
        while(line != null){
            XML.append(line);
            XML.append("\n");
            line = br.readLine();
        }
        String s = XML.toString();*/
        StringBuilder decomp = new StringBuilder(s);
        StringBuilder test = new StringBuilder("");
        String before_last = "";
        StringBuilder result = new StringBuilder("");
        for(int r = 0 ; r< decomp.length() ; r++){
            test.delete(0, test.length());
            while(decomp.charAt(r) != ' '){
                test.append(decomp.charAt(r));
                r++;
            }
            if(Integer.parseInt(test.toString()) < tokens.size()){
                result.append(tokens.get(Integer.parseInt(test.toString())));
                if(r == 0) before_last = tokens.get(Integer.parseInt(test.toString()));
                else{
                    tokens.add(before_last + tokens.get(Integer.parseInt(test.toString())).charAt(0));
                    before_last = tokens.get(Integer.parseInt(test.toString()));
                }
            }
        }
        return result.toString();
    }
}