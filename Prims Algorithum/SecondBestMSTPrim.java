import java.util.*;

public class SecondBestMSTPrim {
    private int V;
    private int[][] graph;

    public SecondBestMSTPrim(int V) {
        this.V = V;
        this.graph = new int[V][V];
    }

    public void addEdge(int u, int v, int w) {
        graph[u][v] = w;
        graph[v][u] = w;
    }

    private int prim(int forbidU, int forbidV) {
        boolean[] inMST = new boolean[V];
        int[] key = new int[V], parent = new int[V];
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        key[0] = 0;

        for (int count = 0; count < V; count++) {
            int u = -1, min = Integer.MAX_VALUE;
            for (int v = 0; v < V; v++) {
                if (!inMST[v] && key[v] < min) {
                    min = key[v];
                    u = v;
                }
            }
            if (u == -1) return Integer.MAX_VALUE;
            inMST[u] = true;
            for (int v = 0; v < V; v++) {
                if (!inMST[v] && graph[u][v] > 0) {
                    if ((u == forbidU && v == forbidV) || (u == forbidV && v == forbidU)) continue;
                    if (graph[u][v] < key[v]) {
                        key[v] = graph[u][v];
                        parent[v] = u;
                    }
                }
            }
        }

        int total = 0;
        for (int i = 0; i < V; i++) {
            if (key[i] == Integer.MAX_VALUE) return Integer.MAX_VALUE;
            total += key[i];
        }
        return total;
    }

    public void findSecondBest() {
        int best = prim(-1, -1);
        System.out.println("Minimum spanning tree weight = " + best);

        boolean[] inMST = new boolean[V];
        int[] key = new int[V], parent = new int[V];
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        key[0] = 0;

        for (int count = 0; count < V; count++) {
            int u = -1, min = Integer.MAX_VALUE;
            for (int v = 0; v < V; v++) {
                if (!inMST[v] && key[v] < min) {
                    min = key[v];
                    u = v;
                }
            }
            inMST[u] = true;
            for (int v = 0; v < V; v++) {
                if (!inMST[v] && graph[u][v] > 0 && graph[u][v] < key[v]) {
                    key[v] = graph[u][v];
                    parent[v] = u;
                }
            }
        }

        int secondBest = Integer.MAX_VALUE;
        for (int v = 1; v < V; v++) {
            int u = parent[v];
            int w = prim(u, v);
            if (w > best && w < secondBest) {
                secondBest = w;
            }
        }

        if (secondBest == Integer.MAX_VALUE) {
            System.out.println("Second Best MST does not exist");
        } else {
            System.out.println("Second Best MST weight    = " + secondBest);
        }
    }

    public static void main(String[] args) {
        int V = 4;
        SecondBestMSTPrim g = new SecondBestMSTPrim(V);
        g.addEdge(0, 1, 10);
        g.addEdge(0, 2, 6);
        g.addEdge(0, 3, 5);
        g.addEdge(1, 3, 15);
        g.addEdge(2, 3, 4);
        g.findSecondBest();
    }
}
