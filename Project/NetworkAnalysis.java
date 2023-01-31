import java.util.ArrayList;
import java.util.List;

// a class containing the 4 main Network Analysis functions: mostInfluencer, mostActive, mutualFollowers and suggestions

public class NetworkAnalysis {
    public static GraphNode mostInfluencer(Graph graph, List<GraphNode> graphNodeList){ // the most influencer is the user that has the max indegree i.e has the max number of followers
        int max=0,index=1;
        for(int i=1;i<=graph.v;i++){
            if (graph.inDegree[i] > max){
                max = graph.inDegree[i];
                index = i;
            }
        }
        return graphNodeList.get(index-1);
    }

    public static GraphNode mostActive(Graph graph,List<GraphNode> graphNodeList){  // the most active user is the one having the max sum of indegree and out degree
        int max=0,index=1;
        for(int i=1;i<=graph.v;i++){
            if (graph.inDegree[i]+graph.outDegree[i] > max){
                max = graph.inDegree[i]+graph.outDegree[i];
                index = i;
            }
        }
        return graphNodeList.get(index-1);
    }

	// here we iterate over the users ids and when someone's id makes our addition equal 2 then this is a mutual follower
	
    public static List<GraphNode> mutualFollowers(Graph graph,List<GraphNode> graphNodeList, GraphNode A, GraphNode B){   
        List<GraphNode> mutualFollowers = new ArrayList<>();
        for(int i=1;i<=graph.v;i++){
	    // if 1->2 and 1->3 then graph.graph[1][2]==1 is true, also graph.graph[1][3]==1 is true so the addition is equal to 2
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
}
