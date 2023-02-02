import java.util.*;
public class GraphConstruction{
    public static List<GraphNode> treeToUsersArray(Node users){
        // we have the root of the tree --> users
        // we should get the user user user
        List<GraphNode> graphNodeList= new ArrayList<>();
        List<Node> usersList=users.getChildren();
        for(Node user:usersList){ // we here iterate over the users
            GraphNode simpleNode=new GraphNode(); // we create a simple graph node instead of the old node we have
            List<Node> userData=user.getChildren(); // get the user data i.e {id, name, list of followers, list of posts, list of topics}
            for(Node user_d:userData){
                if(user_d.getTag().equals("id")) simpleNode.id=user_d.getData();
                if(user_d.getTag().equals("name")) simpleNode.name=user_d.getData();
                if(user_d.getTag().equals("posts")){
                    List<Node> posts=user_d.getChildren();
                    for(Node post:posts){
                        Node body=post.getChildren().get(0); // the body is the first child of the post tag
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

    public static Graph construct(List<GraphNode> graphNodeList){ // using the graph node users list we create the graph using ids
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

    public static void search(List<GraphNode> graphNodeList, String word){ // search for a word by checking all posts and topics
        boolean found=false;
        for(GraphNode user:graphNodeList){
            for (String topic:user.topics){
                if(topic.contains(word)) {
                    System.out.println("User: "+user.name);
                    System.out.println(topic);
                    found=true;
                }
            }
            for (String post:user.posts){
                if (post.contains(word)) {
                    System.out.println("User: "+user.name);
                    System.out.println(post);
                    found=true;
                }
            }
        }
        if(!found) System.out.println("The word you are searching for isn't found!");
    }
}