package XML_Functions;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.*;
import java.util.*;
import java.util.zip.*;
/**
 *
 * @author moham
 */
public class Compression {
    static ArrayList<String> tokens = new ArrayList<String>();
    public static String compress(String source) throws IOException{
        FileReader fr = new FileReader(source);    
        BufferedReader br=new BufferedReader(fr);
        String line = br.readLine();
        StringBuffer XML = new StringBuffer(line);
        while(line != null){
            XML.append(line);
            XML.append("\n");
            line = br.readLine();
        }
        String s = XML.toString();
        ArrayList<String> letters = new ArrayList<String>();
        tokens = letters;
        StringBuilder sb = new StringBuilder("");
        String replace;
        int index;
        String[] chars = XML.toString().split("");
        for(int i = 0; i<chars.length ; i++){
            if(!letters.contains(chars[i])){
                letters.add(chars[i]);
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
            while(letters.contains(temp.toString()) && j <s.length()){
                temp.append(s.charAt(j));
//                if(s.charAt(j) == '\n') sb.append('\n');
                j++;      
            }
            if(letters.contains(temp.toString())) {
                replace = temp.toString();
                i++;
            }
            else replace = temp.substring(0 , temp.length() - 1);
            if(!letters.contains(temp.toString())) letters.add(temp.toString());
            index = letters.indexOf(replace);
            sb.append(index);
            sb.append(' ');
            if(i == s.length() - 1) break;
            i += temp.length() - 1;
        }
       return sb.toString();
    }
    public static String decompress(String s){
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
