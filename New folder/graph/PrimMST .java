import java.util.*;

class PrimMST {
    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    private int V;  
    private List<Edge> edges;  
    private Map<Integer, List<Edge>> adjList;

    PrimMST(int V) {
        this.V = V;
        this.edges = new ArrayList<>();
        this.adjList = new HashMap<>();
    }

    void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
        adjList.computeIfAbsent(src, k -> new ArrayList<>()).add(new Edge(src, dest, weight));
        adjList.computeIfAbsent(dest, k -> new ArrayList<>()).add(new Edge(dest, src, weight));
    }

    int findMSTWeight() {
        boolean[] inMST = new boolean[V];
        int[] key = new int[V];
        int[] parent = new int[V];  
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;  

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0});  

        int mstWeight = 0;
        while (!pq.isEmpty()) {
            int[] minEdge = pq.poll();
            int u = minEdge[1];

            if (inMST[u]) continue;
            inMST[u] = true;
            mstWeight += minEdge[0];

            for (Edge edge : adjList.getOrDefault(u, new ArrayList<>())) {
                int v = edge.dest;
                if (!inMST[v] && edge.weight < key[v]) {
                    key[v] = edge.weight;
                    parent[v] = u;
                    pq.offer(new int[]{key[v], v});
                }
            }
        }

        return mstWeight;
    }

    int countDistinctMSTs() {
        int mstWeight = findMSTWeight();
        if (mstWeight == Integer.MAX_VALUE) return 0;

        boolean[] inMST = new boolean[V];
        int[] key = new int[V];
        int[] parent = new int[V];  
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;  

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0});

        Map<Integer, Integer> weightChoices = new HashMap<>();
        List<Edge> mstEdges = new ArrayList<>();

        while (!pq.isEmpty()) {
            int[] minEdge = pq.poll();
            int u = minEdge[1];

            if (inMST[u]) continue;
            inMST[u] = true;

            if (parent[u] != -1) {
                mstEdges.add(new Edge(parent[u], u, key[u]));
            }

            for (Edge edge : adjList.getOrDefault(u, new ArrayList<>())) {
                int v = edge.dest;
                if (!inMST[v] && edge.weight < key[v]) {
                    key[v] = edge.weight;
                    parent[v] = u;
                    pq.offer(new int[]{key[v], v});
                }
            }
        }

        // Count how many times each weight is used in MST
        for (Edge e : mstEdges) {
            weightChoices.put(e.weight, weightChoices.getOrDefault(e.weight, 0) + 1);
        }

        // Compute the number of distinct MSTs
        int distinctMSTCount = 1;
        for (int count : weightChoices.values()) {
            distinctMSTCount *= factorial(count);
        }

        return distinctMSTCount;
    }

    private int factorial(int n) {
        int fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    public static void main(String[] args) {
        PrimMST graph = new PrimMST(4);
        graph.addEdge(0, 1, 1);  
        graph.addEdge(1, 2, 1);  
        graph.addEdge(2, 3, 2);  
        graph.addEdge(0, 3, 1);  
        graph.addEdge(0, 2, 3);  

        System.out.println("Number of distinct MSTs: " + graph.countDistinctMSTs());
    }
}
