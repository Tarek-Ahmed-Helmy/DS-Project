import java.util.ArrayList;  // this is an undirected graph, and we need to have a directed graph I think

class GraphNode {
	// data members of the graphNode
	public String id;
	public String name;
	public ArrayList<String> posts=new ArrayList<String>();
	public ArrayList<String> topics=new ArrayList<String>();
	public ArrayList<String> followersIds=new ArrayList<String>();
}


