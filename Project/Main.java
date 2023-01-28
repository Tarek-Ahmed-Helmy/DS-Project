import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String []args)throws Exception{
        File file = new File("..\\sample-input.xml");
        Reader fr1 = new FileReader(file);
        BufferedReader br1 = new BufferedReader(fr1);
        StringBuilder sb = new StringBuilder();
        String line = br1.readLine();
        while(line !=null){
            sb.append(line).append("\n");
            line = br1.readLine();
        }
        String xml2string = sb.toString();
        Tree xmlTree = new Tree();
        xmlTree.fillTree(xml2string);
        List<GraphNode> users=GraphConstruction.treeToUsersArray(xmlTree.getRoot());
        Graph constructedGraph = GraphConstruction.construct(users);
        fr1.close();
        br1.close();


        while (true){
            FileReader fr2=new FileReader("..\\sample-input.xml");
            BufferedReader br2=new BufferedReader(fr2);
            int op;
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the operation number: ");
            op = in.nextInt();
            switch (op) {
                case 1:
                    if (ErrorHandling.consistency(br2)) System.out.println("The XML File Is Consistent");
                    else System.out.println("The XML File Is Not Consistent");
                    break;
                case 2:
                    ErrorHandling.showError(br2);
                    break;
                case 3:
                    FileWriter fw=new FileWriter("..\\new-sample-input.xml");
                    BufferedWriter bw=new BufferedWriter(fw);
                    ErrorHandling.solveError(br2, bw);
                    System.out.println("Errors Solved Successfully");
                    bw.close();
                    break;
                case 4:
                    //formatting
                    break;
                case 5:
                    //converting
                    break;
                case 6:
                    //minifying
                    break;
                case 7:
                    //compressing
                    break;
                case 8:
                    //decompressing
                    break;
                case 9:
                    //most influ.
                    break;
                case 10:
                    //mutual followers
                    break;
                case 11:
                    //most active
                    break;
                case 12:
                    //suggestions
                case 13:
                    //search
                default:
                    System.out.println("invalid entry");
                    break;
            }
            fr2.close();
            br2.close();
        }
    }
}