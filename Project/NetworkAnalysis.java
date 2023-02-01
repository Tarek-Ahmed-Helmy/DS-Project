import java.util.ArrayList;
import java.util.List;

// a class containing the 4 main Network Analysis functions: mostInfluencer, mostActive, mutualFollowers and suggestions

public class NetworkAnalysis {
    public static GraphNode mostInfluencer(Graph graph, List<GraphNode> graphNodeList){ // the most influencer is the user that has the max in-degree i.e. has the max number of followers
        int max=0,index=1;
        for(int i=1;i<=graph.v;i++){
            if (graph.inDegree[i] > max){
                max = graph.inDegree[i];
                index = i;
            }
        }
        return graphNodeList.get(index-1);
    }

    public static GraphNode mostActive(Graph graph,List<GraphNode> graphNodeList){  // the most active user is the one having the max sum of in-degree and out degree
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
	
    public static List<GraphNode> mutualFollowers(Graph graph,List<GraphNode> graphNodeList, int A, int B){
        if((A>graphNodeList.size()||A<1)&&(B<=graphNodeList.size()&&B>0)) {
            System.out.println("The first user does not exist!");
            return null;
        }
        else if((B>graphNodeList.size()||B<1)&&(A<=graphNodeList.size()&&A>0)) {
            System.out.println("The second user does not exist!");
            return null;
        }
        else if((B>graphNodeList.size()||B<1)&&(A>graphNodeList.size()||A<1))
        {
            System.out.println("Those users do not exist!");
            return null;
        }
        List<GraphNode> mutualFollowers = new ArrayList<>();
        for(int i=1;i<=graph.v;i++){
	    // if 1->2 and 1->3 then graph.graph[1][2]==1 is true, also graph.graph[1][3]==1 is true so the addition is equal to 2
            if ((graph.graph[i][A] + graph.graph[i][B])==2){
                mutualFollowers.add(graphNodeList.get(i-1));
            }
        }
        return mutualFollowers;
    }

	// here we want to get to the followers of the followers, so we iterate over the followers for the desired user
	// and for every follower we iterate over his followers. if one of them is not following the desired user then we suggest him.
    public static List<GraphNode> suggestions(List<GraphNode> graphNodeList, int id){
        if(id<1||id>graphNodeList.size()){
            System.out.println("This user does not exist!");
            return null;
        }
        GraphNode A=graphNodeList.get(id-1);
        List<GraphNode> suggestionsList = new ArrayList<>();
        int[] freq = new int [graphNodeList.size()+1];
        for(int i=0;i<=graphNodeList.size();i++) freq[i]=0;
        freq[Integer.parseInt(A.id)]=1;  // initialize the desired user to be 1, so we don't take him in count of the suggestions list
	
        for (String follower:A.followersIds)  // this for each loop to initialize the desired user followers with 1's, so we don't suggest them again.
            freq[Integer.parseInt(follower)]=1;

        for (String follower:A.followersIds){
            for(String followerA:graphNodeList.get(Integer.parseInt(follower)-1).followersIds){
                if(freq[Integer.parseInt(followerA)]==0){  // here any follower of the followers that isn't there in our freq list is taken into the suggestions list
                    suggestionsList.add(graphNodeList.get(Integer.parseInt(followerA)-1));
                    freq[Integer.parseInt(followerA)]=1;
                }
            }
        }
        return suggestionsList;
    }
}