import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class Graphviz{
    public static void dotGen(List<GraphNode> users) throws IOException {
        FileWriter fw = new FileWriter("graph.dot");
        fw.write("digraph {\nrankdir=LR;\nnode [shape=circle, style=filled, fillcolor=lightblue, fontname=Arial, fontsize=14];\n");

        for (int i=0;i<users.size();i++){
            fw.write(GraphNode.getNode(users, i).id + " [label=\"id " + GraphNode.getNode(users, i).id + "\"];\n");
            for(int j=0;j<GraphNode.getNode(users, i).followersIds.size();j++){
                fw.write(GraphNode.getNode(users, i).id + "->" +GraphNode.getNode(users, i).followersIds.get(j)+"\n");
            }
        }
        fw.write("}");
        fw.close();
    }}