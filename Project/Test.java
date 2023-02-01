import java.io.*;

public class Test {
    public static void main(String []args) throws IOException {

        FileReader fr=new FileReader("..\\sample-input.xml");
        BufferedReader br=new BufferedReader(fr);
        StringBuilder sb2 = new StringBuilder();
        String line2 = br.readLine();
        while(line2 !=null){
            sb2.append(line2).append("\n");
            line2 = br.readLine();
            // correctedxml +=line2;
        }

        String xml2string = sb2.toString();
        if(ErrorHandling.consistency(xml2string))
            System.out.println("All good");
        else
            System.out.println("Not consistent");
        FileWriter fw=new FileWriter("..\\sample-input.xml");
        BufferedWriter bw=new BufferedWriter(fw);
        ErrorHandling.solveError(xml2string, bw);
        fr=new FileReader("..\\sample-input.xml");
         br=new BufferedReader(fr);
        sb2 = new StringBuilder();
         line2 = br.readLine();
        while(line2 !=null){
            sb2.append(line2).append("\n");
            line2 = br.readLine();
            // correctedxml +=line2;
        }

        xml2string = sb2.toString();
        if(ErrorHandling.consistency(xml2string))
            System.out.println("All good");
        else
            System.out.println("Not consistent");
    }
}
