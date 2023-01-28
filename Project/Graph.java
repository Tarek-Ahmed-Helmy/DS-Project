public class Graph {
    int[][] graph;
    int [] inDegree,outDegree;
    int v;
    public Graph(int n) {
        this.v = n;
        graph = new int[n+1][n+1];
        inDegree = new int [n+1];
        outDegree = new int [n+1];
        for(int i=1;i<=n;i++) {
            inDegree[i] = 0;
            outDegree[i] = 0;
            for (int j=1;j<=n;j++)
                graph[i][j]=0;
        }

    }
    void addEdge(int start, int end) {
        graph[start][end]=1;
        outDegree[start]++;
        inDegree[end]++;
    }
}