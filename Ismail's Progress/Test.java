import java.util.ArrayList;  // this is a undirected graph and we neeed to have a directed graph i think
 class Graph {
	ArrayList<ArrayList<Integer>> graph;
	int v;
	public Graph( int nodes) {
		v=nodes;
		graph=new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<v;i++) {
			graph.add(new ArrayList<Integer>());
		}
	}
		void addEdge(int start, int end) {
			graph.get(start).add(end);
		}
		
		void printGraph() {
			for(int i=0;i<v;i++) {
				System.out.println("Node "+i);
				for(int x:graph.get(i)) System.out.println("--> "+x);
			}
		}

}

public class Test{
	public static void main(String []args) {
		Graph g1=new Graph(5);
		g1.addEdge(0, 1);
		g1.addEdge(1, 2);
		g1.addEdge(1, 3);
		g1.addEdge(0, 4);
		g1.printGraph();
	}
}
