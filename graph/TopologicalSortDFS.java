import java.util.*;

public class TopologicalSortDFS {
    static class Graph {
        private int V; 
        private ArrayList<Integer>[] adj; 
        public Graph(int V) {
            this.V = V;
            adj = new ArrayList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new ArrayList<>();
            }
        }
        public void addEdge(int src, int dest) {
            adj[src].add(dest);
        }
        private void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack) {
            visited[v] = true;
            for (int neighbor : adj[v]) {
                if (!visited[neighbor]) {
                    topologicalSortUtil(neighbor, visited, stack);
                }
            }
            stack.push(v);
        }
        public void topologicalSort() {
            Stack<Integer> stack = new Stack<>();
            boolean visited[] = new boolean[V];
            for (int i = 0; i < V; i++) {
                if (!visited[i]) {
                    topologicalSortUtil(i, visited, stack);
                }
            }
            System.out.print("Topological Order: ");
            while (!stack.isEmpty()) {
                System.out.print(stack.pop() + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        System.out.println("Performing Topological Sort using DFS:");
        g.topologicalSort();
    }
}
