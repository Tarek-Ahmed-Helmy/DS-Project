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
        FileReader fr2 = new FileReader("..\\new-sample-input.xml");
        BufferedReader br2 = new BufferedReader(fr2);
        StringBuilder sb = new StringBuilder();
        String line = br2.readLine();
        while(line !=null){
            sb.append(line).append("\n");
            line = br2.readLine();
        }
        String xml2string = sb.toString();
        Tree xmlTree = new Tree();
        xmlTree.fillTree(xml2string);
        List<GraphNode> users=GraphConstruction.treeToUsersArray(xmlTree.getRoot());
        Graph constructedGraph = GraphConstruction.construct(users);
        br1.close();
        br2.close();
        bw1.close();
        while (true){
            FileReader fr3=new FileReader("..\\sample-input.xml");
            BufferedReader br3=new BufferedReader(fr3);
            int op;
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the operation number: ");
            op = in.nextInt();
            switch (op) {
                case 1:
                    if (ErrorHandling.consistency(br3)) System.out.println("The XML File Is Consistent");
                    else System.out.println("The XML File Is Not Consistent");
                    break;
                case 2:
                    ErrorHandling.showError(br3);
                    break;
                case 3:
                    FileWriter fw2=new FileWriter("..\\new-sample-input.xml");
                    BufferedWriter bw2=new BufferedWriter(fw2);
                    ErrorHandling.solveError(br3, bw2);
                    System.out.println("Errors Solved Successfully");
                    bw2.close();
                    break;
                case 4:
                    String formattedString = Formatting.formatXML(xml2string);
                    System.out.println(formattedString);
                    break;
                case 5:
                    JsonConversion.printJSON(xmlTree.getRoot());
                    break;
                case 6:
                    String minifiedString = Formatting.minify(xml2string);
                    System.out.println(minifiedString);
                    break;
                case 7:
                    FileWriter fw3=new FileWriter("..\\sample-input-compressed.xml");
                    BufferedWriter bw3=new BufferedWriter(fw3);
                    System.out.println(Compression.compress(br3,bw3));
                    System.out.println("File Compressed Successfully");
                    bw3.close();
                    break;
                case 8:
                    FileReader fr4=new FileReader("..\\sample-input-compressed.xml");
                    BufferedReader br4=new BufferedReader(fr4);
                    System.out.println(Compression.decompress(br4));
                    br4.close();
                    break;
                case 9:
                    GraphNode mostInfluencer = NetworkAnalysis.mostInfluencer(constructedGraph, users);
                    System.out.println(mostInfluencer.name + ": " + mostInfluencer.id);
                    break;
                case 10:
                    int user1ID = in.nextInt();
                    int user2ID = in.nextInt();
                    List<GraphNode> mutualFollowers = NetworkAnalysis.mutualFollowers(constructedGraph, users,user1ID, user2ID);
                    if(mutualFollowers==null)
                        break;
                    for (GraphNode user:mutualFollowers){
                        System.out.println(user.name + ": " + user.id);
                    }
                    break;
                case 11:
                    GraphNode mostActive = NetworkAnalysis.mostActive(constructedGraph, users);
                    System.out.println(mostActive.name + ": " + mostActive.id);
                    break;
                case 12:
                    int user_ = in.nextInt();
                    List<GraphNode> suggestionFollowers = NetworkAnalysis.suggestions(users, users.get(user_-1));
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
            br3.close();
        }
    }
}