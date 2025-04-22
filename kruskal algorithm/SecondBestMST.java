import java.util.*;

public class SecondBestMST {
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static class Subset {
        int parent, rank;
    }

    int V, E;
    Edge[] edges;

    public SecondBestMST(int v, int e) {
        V = v;
        E = e;
        edges = new Edge[E];
        for (int i = 0; i < E; i++) {
            edges[i] = new Edge();
        }
    }

    int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
        if (subsets[xroot].rank < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else if (subsets[xroot].rank > subsets[yroot].rank) {
            subsets[yroot].parent = xroot;
        } else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    int kruskal(int ignoreIndex, List<Integer> used) {
        Edge[] sorted = edges.clone();
        Arrays.sort(sorted);
        Subset[] subsets = new Subset[V];
        for (int i = 0; i < V; i++) {
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }
        int e = 0, i = 0, totalWeight = 0;
        while (e < V - 1 && i < E) {
            Edge next = sorted[i++];
            int idx = indexOf(next);
            if (idx == ignoreIndex) continue;
            int x = find(subsets, next.src);
            int y = find(subsets, next.dest);
            if (x != y) {
                union(subsets, x, y);
                totalWeight += next.weight;
                if (used != null) used.add(idx);
                e++;
            }
        }
        return (e == V - 1) ? totalWeight : Integer.MAX_VALUE;
    }

    int indexOf(Edge target) {
        for (int i = 0; i < E; i++) {
            Edge e = edges[i];
            if (e.src == target.src && e.dest == target.dest && e.weight == target.weight) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int V = 4, E = 5;
        SecondBestMST g = new SecondBestMST(V, E);

        g.edges[0].src = 0; g.edges[0].dest = 1; g.edges[0].weight = 10;
        g.edges[1].src = 0; g.edges[1].dest = 2; g.edges[1].weight = 6;
        g.edges[2].src = 0; g.edges[2].dest = 3; g.edges[2].weight = 5;
        g.edges[3].src = 1; g.edges[3].dest = 3; g.edges[3].weight = 15;
        g.edges[4].src = 2; g.edges[4].dest = 3; g.edges[4].weight = 4;

        List<Integer> used = new ArrayList<>();
        int best = g.kruskal(-1, used);
        int secondBest = Integer.MAX_VALUE;
        for (int idx : used) {
            int w = g.kruskal(idx, null);
            if (w > best && w < secondBest) {
                secondBest = w;
            }
        }

        System.out.println("Minimum Spanning Tree weight = " + best);
        if (secondBest < Integer.MAX_VALUE) {
            System.out.println("Second Best MST weight    = " + secondBest);
        } else {
            System.out.println("Second Best MST does not exist");
        }
    }
}
