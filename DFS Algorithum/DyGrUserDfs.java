import java.util.*;

public class DyGrUserDfs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1) Read graph size
        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        // 2) Initialize adjacency list
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // 3) Read edges
        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();
        System.out.println("Enter each edge as two integers (u v), zero‑based:");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            // undirected graph
            adj[u].add(v);
            adj[v].add(u);
        }

        // 4) Read start node
        System.out.print("Enter start vertex (0 to " + (n-1) + "): ");
        int start = sc.nextInt();

        // 5) Run iterative DFS
        iterativeDFS(adj, n, start);

        sc.close();
    }

    static void iterativeDFS(List<Integer>[] adj, int n, int start) {
        boolean[] visited = new boolean[n];
        int[] stack = new int[n];
        int top = 0;

        // mark and push start
        visited[start] = true;
        stack[top++] = start;
        System.out.print("DFS order: " + start);

        // while stack not empty
        while (top != 0) {
            int node = stack[top - 1];
            boolean foundUnvisited = false;

            // scan neighbours
            for (int nbr : adj[node]) {
                if (!visited[nbr]) {
                    // visit & push
                    visited[nbr] = true;
                    stack[top++] = nbr;
                    System.out.print(" -> " + nbr);
                    foundUnvisited = true;
                    break;
                }
            }

            // if no unvisited neighbour, pop
            if (!foundUnvisited) {
                top--;
            }
        }
        System.out.println();
    }
}
