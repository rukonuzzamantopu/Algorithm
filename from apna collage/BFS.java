import java.util.*;

public class BFS {
    static class Edge {
        int src;
        int dest;
        int wt;

        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    static void createGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0, 1, 1));
        graph[0].add(new Edge(0, 2, 1));

        graph[1].add(new Edge(1, 0, 1));
        graph[1].add(new Edge(1, 3, 1));

        graph[2].add(new Edge(2, 0, 1));
        graph[2].add(new Edge(2, 4, 1));

        graph[3].add(new Edge(3, 1, 1));
        graph[3].add(new Edge(3, 4, 1));
        graph[3].add(new Edge(3, 5, 1));

        graph[4].add(new Edge(4, 2, 1));
        graph[4].add(new Edge(4, 3, 1));
        graph[4].add(new Edge(4, 5, 1));

        graph[5].add(new Edge(5, 3, 1));
        graph[5].add(new Edge(5, 4, 1));
        graph[5].add(new Edge(5, 6, 1));

        // FIXED: add the 6→5 edge into graph[6], not graph[5]
        graph[6].add(new Edge(6, 5, 1));
    }

    // BFS from a given source, using the shared visited[] array
    public static void bfs(ArrayList<Edge> graph[], boolean[] visited, int src) {
        Queue<Integer> q = new LinkedList<>();
        visited[src] = true;
        q.add(src);

        while (!q.isEmpty()) {
            int curr = q.remove();
            System.out.print(curr + " ");
            for (Edge e : graph[curr]) {
                if (!visited[e.dest]) {
                    visited[e.dest] = true;
                    q.add(e.dest);
                }
            }
        }
    }

    public static void main(String args[]) {
        /*
         * 1 --- 3
         * /      | \
         * 0       |   5 -- 6
         * \      |  /
         * 2 ---- 4
         */
        int V = 7;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);

        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                // New disconnected component found
                bfs(graph, visited, i);
                System.out.println();  // newline between components
            }
        }
    }
}
