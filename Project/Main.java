import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String []args)throws Exception{
        FileReader fr1 = new FileReader("..\\sample-input.xml");
        BufferedReader br1 = new BufferedReader(fr1);
        FileWriter fw1=new FileWriter("..\\new-sample-input.xml");
        BufferedWriter bw1=new BufferedWriter(fw1);
        ErrorHandling.solveError(br1, bw1);
        bw1.close();
        FileReader fr3 = new FileReader("..\\new-sample-input.xml");
        BufferedReader br3 = new BufferedReader(fr3);
        StringBuilder sb = new StringBuilder();
        String line = br3.readLine();
        while(line !=null){
            sb.append(line).append("\n");
            line = br3.readLine();
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
                    FileWriter fw2=new FileWriter("..\\new-sample-input.xml");
                    BufferedWriter bw2=new BufferedWriter(fw2);
                    ErrorHandling.solveError(br2, bw2);
                    System.out.println("Errors Solved Successfully");
                    bw2.close();
                    break;
                case 4:
                    String formattedString = Formatting.FormatXML(xml2string);
                    System.out.println(formattedString);
                    break;
                case 5:
                    JsonConversion.printJSON(xmlTree.getRoot());
                    break;
                case 6:
                    String minifiedString = Formatting.Minify(xml2string);
                    System.out.println(minifiedString);
                    break;
                case 7:
                    //compressing
                    break;
                case 8:
                    //decompressing
                    break;
                case 9:
                    GraphNode mostInfluencer = GraphConstruction.mostInfluencer(constructedGraph, users);
                    System.out.println(mostInfluencer.name + ": " + mostInfluencer.id);
                    break;
                case 10:
                    GraphNode user1 = new GraphNode();
                    GraphNode user2 = new GraphNode();
                    List<GraphNode> mutualFollowers = GraphConstruction.mutualFollowers(constructedGraph, users, user1, user2);
                    for (GraphNode user:mutualFollowers){
                        System.out.println(user.name + ": " + user.id);
                    }
                    break;
                case 11:
                    GraphNode mostActive = GraphConstruction.mostActive(constructedGraph, users);
                    System.out.println(mostActive.name + ": " + mostActive.id);
                    break;
                case 12:
                    GraphNode userx = new GraphNode();
                    List<GraphNode> suggestionFollowers = GraphConstruction.suggestions(users, userx);
                    for (GraphNode user:suggestionFollowers){
                        System.out.println(user.name + ": " + user.id);
                    }
                    break;
                case 13:
                    String word;
                    word = in.next();
                    GraphConstruction.search(users,word);
                    break;
                default:
                    System.out.println("invalid entry");
                    break;
            }
            fr2.close();
            br2.close();
        }
    }
}