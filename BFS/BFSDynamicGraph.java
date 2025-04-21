import java.util.*;

public class BFSDynamicGraph {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read number of vertices and edges
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        // Build adjacency list
        @SuppressWarnings("unchecked")
        List<Integer>[] adj = new List[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

        // Read edges
        System.out.println("Enter each edge as two vertex indices (0 to " + (V-1) + "):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            // for undirected graph; swap or remove one line for directed
            adj[u].add(v);
            adj[v].add(u);
        }

        // Read starting vertex for BFS
        System.out.print("Enter starting vertex for BFS: ");
        int src = sc.nextInt();
        sc.close();

        // Perform BFS
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new ArrayDeque<>();

        visited[src] = true;
        queue.offer(src);

        System.out.print("BFS traversal order: ");
        while (!queue.isEmpty()) {
            int u = queue.poll();
            System.out.print(u + " ");

            // Enqueue all unvisited neighbors
            for (int nbr : adj[u]) {
                if (!visited[nbr]) {
                    visited[nbr] = true;
                    queue.offer(nbr);
                }
            }
        }
        System.out.println();
    }
}
