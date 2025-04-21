import java.util.*;

public class DFSPath {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1) Read number of vertices
        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        // 2) Build adjacency list
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // 3) Read edges (undirected)
        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();
        System.out.println("Enter each edge as two integers u v (0-based):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }

        // 4) Read source and destination
        System.out.print("Enter source vertex (0 to " + (n-1) + "): ");
        int src = sc.nextInt();
        System.out.print("Enter destination vertex (0 to " + (n-1) + "): ");
        int dest = sc.nextInt();

        // 5) Run DFS to find a path
        List<Integer> path = findPathDFS(adj, n, src, dest);

        // 6) Print result
        if (path.isEmpty()) {
            System.out.println("No path found from " + src + " to " + dest);
        } else {
            System.out.print("Path: ");
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i) + (i + 1 < path.size() ? " -> " : ""));
            }
            System.out.println();
        }

        sc.close();
    }

    static List<Integer> findPathDFS(List<Integer>[] adj, int n, int src, int dest) {
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        Deque<Integer> stack = new ArrayDeque<>();
        visited[src] = true;
        stack.push(src);

        // iterative DFS with parent tracking
        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (u == dest) break;

            for (int v : adj[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    parent[v] = u;
                    stack.push(v);
                }
            }
        }

        // reconstruct path if destination was reached
        List<Integer> path = new ArrayList<>();
        if (!visited[dest]) {
            return path; // empty => no path
        }

        for (int cur = dest; cur != -1; cur = parent[cur]) {
            path.add(cur);
        }
        Collections.reverse(path);
        return path;
    }
}
