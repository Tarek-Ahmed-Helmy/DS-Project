import java.io.*;
import java.util.*;
public class Compression {
    public static ArrayList<String> tokens = new ArrayList<>();
    public static String compress(BufferedReader br, BufferedWriter bw) throws IOException{
        String line = br.readLine();
        StringBuilder XML = new StringBuilder();
        while(line != null){
            XML.append(line);
            XML.append("\n");
            line = br.readLine();
        }
        String s = XML.toString();
        StringBuilder sb = new StringBuilder();
        String replace;
        int index;
        String[] chars = s.split("");
        for (String aChar : chars) {
            if (!tokens.contains(aChar)) {
                tokens.add(aChar);
            }
        }

        int i = 0;
        int j;
        while( i < s.length()){
            StringBuilder temp = new StringBuilder();
            j = i;
            temp.append(s.charAt(j));
            j++;
            while(tokens.contains(temp.toString()) && j <s.length()){
                temp.append(s.charAt(j));
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
    public static String decompress(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while(line !=null){
            sb.append(line).append("\n");
            line = br.readLine();
        }
        String s = sb.toString();
        StringBuilder current = new StringBuilder();
        StringBuilder result = new StringBuilder();
        for(int r=0; r< s.length()-1; r++){
            current.delete(0, current.length());
            while(s.charAt(r) != ' '){
                current.append(s.charAt(r));
                r++;
            }
            result.append(tokens.get(Integer.parseInt(current.toString())));
        }
        return result.toString();
    }
}