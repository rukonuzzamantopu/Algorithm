import java.util.*;

public class CycleDetectionBFS {
    static class Edge {
        int src, dest;
        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }
    static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0, 1));
        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 2));
        graph[2].add(new Edge(2, 1));
        graph[2].add(new Edge(2, 3));
        graph[3].add(new Edge(3, 2));
        graph[3].add(new Edge(3, 4));
        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 1));
        graph[1].add(new Edge(1, 4));
    }
    static boolean isCyclicBFS(ArrayList<Edge>[] graph, int V) {
        boolean[] visited = new boolean[V];
        int[] parent = new int[V];
        Arrays.fill(parent, -1);
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (bfsDetectCycle(graph, visited, parent, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean bfsDetectCycle(ArrayList<Edge>[] graph, boolean[] visited, int[] parent, int src) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        visited[src] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (Edge edge : graph[node]) {
                int neighbor = edge.dest;
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    parent[neighbor] = node;
                    queue.add(neighbor);
                } else if (neighbor != parent[node]) {
                    System.out.print("Cycle detected: ");
                    printCyclePath(parent, node, neighbor);
                    return true;
                }
            }
        }
        return false;
    }

    static void printCyclePath(int[] parent, int start, int end) {
        List<Integer> cyclePath = new ArrayList<>();
        int node = start;
        while (node != -1) {
            cyclePath.add(node);
            if (node == end) break;
            node = parent[node];
        }
        Collections.reverse(cyclePath);
        cyclePath.add(end);
        for (int i = 0; i < cyclePath.size(); i++) {
            System.out.print(cyclePath.get(i));
            if (i < cyclePath.size() - 1) System.out.print(" -> ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int V = 5;
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);
        if (!isCyclicBFS(graph, V)) {
            System.out.println("No cycle found in the graph.");
        }
    }
}
