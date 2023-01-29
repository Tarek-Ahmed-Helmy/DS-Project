import java.util.*;
public class GraphConstruction{
    public static List<GraphNode> treeToUsersArray(Node users){
        // we have the root of the tree --> users
        // we should get the user user user
        List<GraphNode> graphNodeList= new ArrayList<>();
        List<Node> usersList=users.getChildren();
        for(Node user:usersList){
            GraphNode simpleNode=new GraphNode();
            List<Node> userData=user.getChildren();
            for(Node user_d:userData){
                if(user_d.getTag().equals("id")) simpleNode.id=user_d.getData();
                if(user_d.getTag().equals("name")) simpleNode.name=user_d.getData();
                if(user_d.getTag().equals("posts")){
                    List<Node> posts=user_d.getChildren();
                    for(Node post:posts){
                        Node body=post.getChildren().get(0);
                        simpleNode.posts.add(body.getData());
                        List<Node> topics=post.getChildren().get(1).getChildren();
                            for(Node topic:topics){
                                simpleNode.topics.add(topic.getData());
                            }
                    }
                }
                if(user_d.getTag().equals("followers")) {
                    List<Node> followers=user_d.getChildren();
                    for(Node follower:followers) {
                        Node id=follower.getChildren().get(0);
                        simpleNode.followersIds.add(id.getData());
                    }
                }
            }
            graphNodeList.add(simpleNode);
        }
        return graphNodeList;
    }

    public static Graph construct(List<GraphNode> graphNodeList){
        int n = graphNodeList.size();
        Graph graph = new Graph(n);
        for (GraphNode user:graphNodeList){
            int id = Integer.parseInt(user.id);
            for (String follower:user.followersIds){
                graph.addEdge(Integer.parseInt(follower),id);
            }
        }
        return graph;
    }

    public static GraphNode mostInfluencer(Graph graph,List<GraphNode> graphNodeList){
        int max=0,index=1;
        for(int i=1;i<=graph.v;i++){
            if (graph.inDegree[i] > max){
                max = graph.inDegree[i];
                index = i;
            }
        }
        return graphNodeList.get(index-1);
    }

    public static GraphNode mostActive(Graph graph,List<GraphNode> graphNodeList){
        int max=0,index=1;
        for(int i=1;i<=graph.v;i++){
            if (graph.inDegree[i]+graph.outDegree[i] > max){
                max = graph.inDegree[i]+graph.outDegree[i];
                index = i;
            }
        }
        return graphNodeList.get(index-1);
    }

    public static List<GraphNode> mutualFollowers(Graph graph,List<GraphNode> graphNodeList, GraphNode A, GraphNode B){
        List<GraphNode> mutualFollowers = new ArrayList<>();
        for(int i=1;i<=graph.v;i++){
            if ((graph.graph[i][Integer.parseInt(A.id)] + graph.graph[i][Integer.parseInt(B.id)])==2){
                mutualFollowers.add(graphNodeList.get(i-1));
            }
        }
        return mutualFollowers;
    }

    public static List<GraphNode> suggestions(List<GraphNode> graphNodeList, GraphNode A){
        List<GraphNode> suggestionsList = new ArrayList<>();
        int[] freq = new int [graphNodeList.size()+1];
        for(int i=0;i<=graphNodeList.size();i++) freq[i]=0;
        freq[Integer.parseInt(A.id)]=1;

        for (String follower:A.followersIds)
            freq[Integer.parseInt(follower)]=1;

        for (String follower:A.followersIds){
            for(String followerA:graphNodeList.get(Integer.parseInt(follower)-1).followersIds){
                if(freq[Integer.parseInt(followerA)]==0){
                    suggestionsList.add(graphNodeList.get(Integer.parseInt(followerA)-1));
                    freq[Integer.parseInt(followerA)]=1;
                }
            }
        }
        return suggestionsList;
    }

    public static void search(List<GraphNode> graphNodeList, String word){
        for(GraphNode user:graphNodeList){
            for (String topic:user.topics){
                if(topic.contains(word)) System.out.println(topic);
            }
            for (String post:user.posts){
                if (post.contains(word)) System.out.println(post);
            }
        }
    }
}