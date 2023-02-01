public class Graph {
    int[][] graph;
    int [] inDegree,outDegree;
    int v;
    public Graph(int n) { // constructor that makes a 2d adjacency matrix
        this.v = n;
        graph = new int[n+1][n+1]; // the graph
        inDegree = new int [n+1]; // the in-degree array which has info about the in-degrees of a vertex
        outDegree = new int [n+1]; // the out-degree array which has info about the out-degrees of a vertex
        for(int i=1;i<=n;i++) {
            inDegree[i] = 0;
            outDegree[i] = 0;
            for (int j=1;j<=n;j++)
                graph[i][j]=0;
        }
    }
    void addEdge(int start, int end) {
        graph[start][end]=1; // the row represents the out-degree relation between a vertex and all other vertices
        outDegree[start]++;
        inDegree[end]++;
    }
}