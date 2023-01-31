import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class Graphviz{
    public static void dotgen(String xml2string) throws IOException {
    Tree xmlTree = new Tree();
        xmlTree.fillTree(xml2string);
    List<GraphNode> users=GraphConstruction.treeToUsersArray(xmlTree.getRoot());
    FileWriter writer = new FileWriter("graph.dot");

                    writer.write("digraph {\nrankdir=LR;\nnode [shape=circle, style=filled, fillcolor=lightblue, fontname=Arial, fontsize=14];\n");

                    for (int i=0;i<users.size();i++) {
                        writer.write(GraphNode.getNode(users, i).id + " [label=\"id " + GraphNode.getNode(users, i).id + "\"];\n");
                        
                    for(int j=0;j<GraphNode.getNode(users, i).followersIds.size();j++){
                    writer.write(GraphNode.getNode(users, i).id + "->" +GraphNode.getNode(users, i).followersIds.get(j)+"\n");
                    }}

                    writer.write("}");
                    writer.close();
}}