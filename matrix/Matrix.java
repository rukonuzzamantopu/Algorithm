import java.util.Scanner;

public class Matrix {
    static int[][] matrix = new int[20][20];

    public static void main(String[] args) {
        int e = 7, n = 5;
        inmatrix(e);
        System.out.println("output");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void inmatrix(int e) {
        Scanner sn = new Scanner(System.in);
        System.out.println("Enter the edges (e.g. A B):");
        for (int i = 0; i < e; i++) {
            char j = sn.next().charAt(0);
            char k = sn.next().charAt(0);
            int row = j - 'A';
            int col = k - 'A';
            matrix[row][col] = matrix[col][row] = 1;
        }
        sn.close();
    }
}
