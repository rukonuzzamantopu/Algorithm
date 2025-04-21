import java.util.*;

public class BFSAdjList {
    private final List<Integer>[] adj;  // adjacency list
    private final int V;               // number of vertices

    // Construct a graph with V vertices
    @SuppressWarnings("unchecked")
    public BFSAdjList(int V) {
        this.V = V;
        adj = new List[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    // Add an undirected edge u–v
    public void addEdge(int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
    }

    // Perform BFS starting from 'start' and print visit order
    public void bfs(int start) {
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new ArrayDeque<>();

        visited[start] = true;
        q.offer(start);

        System.out.print("BFS order from " + start + ": ");
        while (!q.isEmpty()) {
            int u = q.poll();
            System.out.print(u + " ");

            for (int nbr : adj[u]) {
                if (!visited[nbr]) {
                    visited[nbr] = true;
                    q.offer(nbr);
                }
            }
        }
        System.out.println();
    }

    // Helper to print the adjacency list
    public void printAdjList() {
        System.out.println("Adjacency List:");
        for (int i = 0; i < V; i++) {
            System.out.print(i + " -> ");
            for (int nbr : adj[i]) {
                System.out.print(nbr + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Example: 6 vertices (0..5), edges as below
        BFSAdjList graph = new BFSAdjList(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        graph.printAdjList();
        graph.bfs(0);
    }
}
