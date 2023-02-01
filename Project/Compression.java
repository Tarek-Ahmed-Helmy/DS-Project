import java.io.*;
import java.util.*;
// the compression technique is inspired by lzw coding
// The LZW compression technique scans a string of symbols, strings are then combined into codes by the algorithm. It makes an entry in a table
// for each input bit pattern, comprising the pattern itself and a shorter code, for each input bit pattern of a specific length,
// let's say 12 bits. The table is sometimes known as a codebook or dictionary. It maintains correspondence between the longest words encountered
// and a list of code values while storing character sequences dynamically selected from the input text.
public class Compression {
    public static ArrayList<String> tokens = new ArrayList<>(); // that's our dictionary.
    public static String compress(String str, BufferedWriter bw) throws IOException{
        StringReader reader = new StringReader(str);
    	BufferedReader br = new BufferedReader(reader);
        String line = br.readLine();
        StringBuilder XML = new StringBuilder();
        while(line != null){
            XML.append(line);
            XML.append("\n");
            line = br.readLine();
        }
        String s = XML.toString();         //Converted XML to String
        StringBuilder sb = new StringBuilder();
        String replace;
        int index;
        String[] chars = s.split("");
        for (String aChar : chars) {         //Adding all characters of XML in chars array
            if (!tokens.contains(aChar)) {
                tokens.add(aChar);
            }
        }

        int i = 0;
        int j;
        while( i < s.length()){
            StringBuilder temp = new StringBuilder();         //Sting Builder of character to be checked
            j = i;
            temp.append(s.charAt(j));
            j++;
            while(tokens.contains(temp.toString()) && j <s.length()){       //Adding chars to temp until the given string is not present in token
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
            sb.append(index);               //replacing the temp with its index in tokens
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
        String s = sb.toString();    // Converting Compressed file to string
        StringBuilder current = new StringBuilder();             //current integer being processed in file
        StringBuilder result = new StringBuilder();           // result string of compressed file
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