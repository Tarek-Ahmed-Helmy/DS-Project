import java.util.ArrayList;
import java.util.List;

class GraphNode {
	// data members of the graphNode
	public String id; 
	public String name;
	public ArrayList<String> posts= new ArrayList<>();
	public ArrayList<String> topics= new ArrayList<>();
	public ArrayList<String> followersIds= new ArrayList<>();

	public static GraphNode getNode (List<GraphNode> graphNodeList, int id){
		return graphNodeList.get(id);
	}
}