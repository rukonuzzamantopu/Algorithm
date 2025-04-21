public class LabDfs {
    // node labels
    static char[] c = { 'A','B','C','D','E','F','G','S' };
    // adjacency list
    static int[][] list = {
        {3, 7},    // A -> D, S
        {4, 7},    // B -> E, S
        {5, 7},    // C -> F, S
        {0, 6},    // D -> A, G
        {1, 6},    // E -> B, G
        {2, 6},    // F -> C, G
        {3, 4, 5}, // G -> D, E, F
        {0, 1, 2}  // S -> A, B, C
    };

    static boolean[] checked = new boolean[c.length];
    static int[] stk = new int[20];
    static int top = 0;

    public static void main(String[] args) {
        // start at node S (index 7)
        push(7);

        while (top != 0) {
            int n = stk[top - 1];
            boolean found = false;

            // look for an unvisited neighbour
            for (int i = 0; i < list[n].length; i++) {
                int nbr = list[n][i];
                if (!checked[nbr]) {
                    push(nbr);
                    found = true;
                    break;
                }
            }

            // if none found, pop back up
            if (!found) {
                pop();
            }
        }
    }

    static void push(int n) {
        checked[n] = true;
        System.out.print(c[n] + " ");
        stk[top++] = n;
    }

    static void pop() {
        int node = stk[--top];
        // if you want to see the pop sequence, uncomment:
        // System.out.print("(" + c[node] + ") ");
    }
}
