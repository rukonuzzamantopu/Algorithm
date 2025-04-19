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
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(i -> key[i]));
        pq.offer(0);

        int mstWeight = 0;
        while (!pq.isEmpty()) {
            int u = pq.poll();
            if (inMST[u]) continue;
            inMST[u] = true;
            mstWeight += key[u];

            for (Edge edge : adjList.get(u)) {
                int v = edge.dest;
                if (!inMST[v] && edge.weight < key[v]) {
                    key[v] = edge.weight;
                    pq.offer(v);
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
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(i -> key[i]));
        pq.offer(0);
        
        Map<Integer, Integer> weightChoices = new HashMap<>();
        
        while (!pq.isEmpty()) {
            int u = pq.poll();
            if (inMST[u]) continue;
            inMST[u] = true;
            
            for (Edge edge : adjList.get(u)) {
                int v = edge.dest;
                if (!inMST[v] && edge.weight < key[v]) {
                    key[v] = edge.weight;
                    pq.offer(v);
                    weightChoices.put(edge.weight, weightChoices.getOrDefault(edge.weight, 0) + 1);
                }
            }
        }

        
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
