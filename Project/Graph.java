public class Graph {
    int[][] graph;
    int [] inDegree,outDegree;
    int v;
    public Graph(int n) { // constructor that makes a 2d adjacency matrix
        this.v = n;
        graph = new int[n+1][n+1]; // the graph
        inDegree = new int [n+1]; // the indegree array which has info about the indegrees of a vertix
        outDegree = new int [n+1]; // the outdegree array which has info about the outdegrees of a vertix
        for(int i=1;i<=n;i++) {
            inDegree[i] = 0;
            outDegree[i] = 0;
            for (int j=1;j<=n;j++)
                graph[i][j]=0;
        }

    }
    void addEdge(int start, int end) {
        graph[start][end]=1; // the row represents the outdegree relation between a vertix and all other vertices 
        outDegree[start]++;
        inDegree[end]++;
    }
}