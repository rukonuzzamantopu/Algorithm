import java.util.*;

public class BreadFS {
    // Node labels
    static char[] c = {'A','B','C','D','E','F','G','S'};
    // Degree (number of neighbors) for each node
    static int[] e = {2,2,2,2,2,2,3,3};
    // Adjacency list
    static int[][] list = {
        {3,7},    // A connects to D,S
        {4,7},    // B → E,S
        {5,7},    // C → F,S
        {0,6},    // D → A,G
        {1,6},    // E → B,G
        {2,6},    // F → C,G
        {3,4,5},  // G → D,E,F
        {0,1,2}   // S → A,B,C
    };

    static boolean[] checked = new boolean[20];
    static int[] que = new int[20];
    static int first = 0, last = 0;

    public static void main(String[] args) {
        // start BFS from node index 7 ('S')
        enq(7);

        System.out.print("BFS order: ");
        while (first < last) {
            int n = dq();
            // enqueue all unvisited neighbors
            for (int i = 0; i < e[n]; i++) {
                int nbr = list[n][i];
                if (!checked[nbr]) {
                    enq(nbr);
                }
            }
        }
        System.out.println();  // newline at end
    }

    // enqueue node n
    static void enq(int n) {
        checked[n] = true;
        que[last++] = n;
    }

    // dequeue and print front node
    static int dq() {
        int node = que[first++];
        System.out.print(c[node] + " ");
        return node;
    }
}
