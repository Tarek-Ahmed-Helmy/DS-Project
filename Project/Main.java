import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String []args)throws Exception{
        FileWriter fw=new FileWriter("D:\\edu\\CSE331, Data Structures and Algorithms\\project\\new-sample-input.xml");
        BufferedWriter bw=new BufferedWriter(fw);
        FileReader fr=new FileReader("D:\\edu\\CSE331, Data Structures and Algorithms\\project\\sample-input.xml");
        BufferedReader br=new BufferedReader(fr);

        int op;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the no. of operation: ");
        op = in.nextInt();
        switch (op){
            case 1:
                if(ErrorHandling.consistency(br)) System.out.println("The XML File Is Consistent");
                else System.out.println("The XML File Is Not Consistent");
                break;
            case 2:
                ErrorHandling.showError(br);
                break;
            case 3:
                ErrorHandling.solveError(br,bw);
                System.out.println("Errors Solved Successfully");
                break;
            default:
                System.out.println("Error 404");
                break;
        }
        bw.close();
        br.close();
    }
}