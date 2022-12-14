import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String []args)throws Exception{
            Pattern pattern= Pattern.compile("<(\\w+)>");
            Matcher matcher;
            FileReader fr=new FileReader("H:\\note.xml");
            BufferedReader br=new BufferedReader(fr);
            String line=br.readLine();
            while(line!=null){
                matcher=pattern.matcher(line);
                if(matcher.find())
                    System.out.println(matcher.group());
                line=br.readLine();
            }


           br.close();
        }
    }

