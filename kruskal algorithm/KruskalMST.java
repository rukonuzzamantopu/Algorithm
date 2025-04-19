import java.util.*;

class KruskalMST {
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;
        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }
    static class DisjointSet {
        int[] parent, rank;
        DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int u) {
            if (parent[u] != u)
                parent[u] = find(parent[u]); 
            return parent[u];
        }
        boolean union(int u, int v) {
            int rootU = find(u), rootV = find(v);
            if (rootU == rootV) return false;
            if (rank[rootU] > rank[rootV]) parent[rootV] = rootU;
            else if (rank[rootU] < rank[rootV]) parent[rootU] = rootV;
            else { parent[rootV] = rootU; rank[rootU]++; }
            return true;
        }
    }
    private int V;
    private List<Edge> edges;
    KruskalMST(int V) {
        this.V = V;
        this.edges = new ArrayList<>();
    }
    void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }
    int findMSTWeight() {
        Collections.sort(edges);
        DisjointSet ds = new DisjointSet(V);
        int mstWeight = 0, edgeCount = 0;
        for (Edge edge : edges) {
            if (ds.union(edge.src, edge.dest)) {
                mstWeight += edge.weight;
                edgeCount++;
                if (edgeCount == V - 1) break;
            }
        }
        return (edgeCount == V - 1) ? mstWeight : -1;
    }
    int countDistinctMSTs() {
        int mstWeight = findMSTWeight();
        if (mstWeight == -1) return 0;
        Map<Integer, Integer> edgeCountByWeight = new HashMap<>();
        for (Edge edge : edges) {
            edgeCountByWeight.put(edge.weight, edgeCountByWeight.getOrDefault(edge.weight, 0) + 1);
        }
        int count = 1;
        for (int countEdges : edgeCountByWeight.values()) {
            count *= factorial(countEdges);
        }
        return count;
    }
    private int factorial(int n) {
        int fact = 1;
        for (int i = 2; i <= n; i++) fact *= i;
        return fact;
    }
    public static void main(String[] args) {
        KruskalMST graph = new KruskalMST(4);
        graph.addEdge(0, 1, 2);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 2);
        graph.addEdge(0, 3, 4);
        graph.addEdge(0, 2, 5);
        graph.addEdge(1, 3, 5);
        System.out.println("Number of distinct MSTs: " + graph.countDistinctMSTs());
    }
}
