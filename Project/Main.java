import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String []args)throws Exception{
        while (true){
            FileReader fr=new FileReader("..\\sample-input.xml");
            BufferedReader br=new BufferedReader(fr);
            int op;
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the operation number: ");
            op = in.nextInt();
            switch (op) {
                case 1:
                    if (ErrorHandling.consistency(br)) System.out.println("The XML File Is Consistent");
                    else System.out.println("The XML File Is Not Consistent");
                    break;
                case 2:
                    ErrorHandling.showError(br);
                    break;
                case 3:
                    FileWriter fw=new FileWriter("..\\new-sample-input.xml");
                    BufferedWriter bw=new BufferedWriter(fw);
                    ErrorHandling.solveError(br, bw);
                    System.out.println("Errors Solved Successfully");
                    bw.close();
                    break;
                default:
                    System.out.println("Error 404");
                    break;
            }
            br.close();
        }
    }
}