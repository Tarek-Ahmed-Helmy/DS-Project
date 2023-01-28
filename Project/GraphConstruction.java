import java.io.*;
import java.util.*;

class Graph {
    int[][] graph;
    int [] indegree,outdegree;
    int v;
    public Graph(int n) {
        this.v = n;
        graph = new int[n+1][n+1];
        indegree = new int [n+1];
        outdegree = new int [n+1];
        for(int i=1;i<=n;i++) {
            indegree[i] = 0;
            outdegree[i] = 0;
            for (int j=1;j<=n;j++)
                graph[i][j]=0;
        }

    }
    void addEdge(int start, int end) {
        graph[start][end]=1;
        outdegree[start]++;
        indegree[end]++;
    }
}

public class GraphConstruction {
        public static List<GraphNode> treeToUsersArray(Node users){
            // we have the root of the tree i.e users
            // we should get the user user user
            List<GraphNode> graphNodeList=new ArrayList<GraphNode>();
            List<Node> usersList=users.getChildren();
            for(Node user:usersList) {
                GraphNode simpleOne=new GraphNode();
                List<Node> userData=user.getChildren();
                for(Node x:userData) {
                    if(x.getTag().equals("id")) simpleOne.id=x.getData();
                    if(x.getTag().equals("name")) simpleOne.name=x.getData();
                    if(x.getTag().equals("posts")) {
                        List<Node> posts=x.getChildren();
                        for(Node y:posts) {
                            Node body=y.getChildren().get(0);
                            simpleOne.posts.add(body.getData());
                        }
                    }
                    if(x.getTag().equals("followers")) {
                        List<Node> followers=x.getChildren();
                        for(Node follower:followers) {
                            Node id=follower.getChildren().get(0);
                            simpleOne.followersIds.add(id.getData());
                        }
                    }
                }
                graphNodeList.add(simpleOne);
            }
            return graphNodeList;
        }

        public static Graph construct(List<GraphNode> graphNodeList){
            int n = graphNodeList.size();
            Graph graph = new Graph(n);
            for (GraphNode user:graphNodeList){
                int id = Integer.valueOf(user.id);
                for (String follower:user.followersIds){
                    graph.addEdge(id,Integer.valueOf(follower));
                }
            }
            return graph;
        }

        public static GraphNode mostInfluencer(Graph graph,List<GraphNode> graphNodeList){
            int max=0,index=1;
            for(int i=1;i<=graph.v;i++) {
                if (graph.indegree[i] > max) {
                    max = graph.indegree[i];
                    index = i;
                }
            }
            return graphNodeList.get(index-1);
        }

        public static GraphNode mostActive(Graph graph,List<GraphNode> graphNodeList){
            int max=0,index=1;
            for(int i=1;i<=graph.v;i++) {
                if (graph.indegree[i]+graph.outdegree[i] > max) {
                    max = graph.indegree[i]+graph.outdegree[i];
                    index = i;
                }
            }
            return graphNodeList.get(index-1);
        }

        public static List<GraphNode> mutualFollowers(Graph graph,List<GraphNode> graphNodeList, GraphNode A, GraphNode B){
            List<GraphNode> mutualFollowers = new ArrayList<GraphNode>();
            for(int i=1;i<=graph.v;i++) {
                if ((graph.graph[Integer.valueOf(A.id)][i] + graph.graph[Integer.valueOf(B.id)][i])==2) {
                    mutualFollowers.add(graphNodeList.get(i-1));
                }
            }
            return mutualFollowers;
        }

        public static List<GraphNode> suggestions(List<GraphNode> graphNodeList, GraphNode A){
            List<GraphNode> suggestionsList = new ArrayList<GraphNode>();
            int freq [] = new int [graphNodeList.size()+1];
            for(int i=0;i<=graphNodeList.size();i++) freq[i]=0;
            freq[Integer.valueOf(A.id)]=1;
            
            for (String follower:A.followersIds)
            	freq[Integer.valueOf(follower.id)]=1;
            
            for (String follower:A.followersIds){
                for(String followerA:follower.followersIds) {
                	if(freq[Integer.valueOf(followerA.id)]==0)
                	{
                		suggestionsList.add(followerA);
                		freq[Integer.valueOf(followerA.id)]=1;
                	}
                }
            }
            return suggestionsList;
        }

        public static 


/*
        public static void main(String[] args) throws IOException {
            // TODO Auto-generated method stub
            File file = new File("/home/ismail939/DS Project Clone 1/DS-Project/sample-input.xml");
            Reader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while(line !=null){
                sb.append(line).append("\n");
                line = br.readLine();
            }
            String xml2string = sb.toString();

            Tree xmltree = new Tree();
            xmltree.fillTree(xml2string);
            List<GraphNode> userss=treeToUsersArray(xmltree.getRoot());



            br.close();
            fr.close();

        }*/

    }
