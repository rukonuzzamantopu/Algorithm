import java.util.*;
import java.lang.*;

public class Lcs {

    public static int lcs(char X[], char Y[], int m, int n) {
        // Base case: if either string is empty
        if (m == 0 || n == 0) {
            return 0;
        }

        // If characters match, move diagonally and add 1
        if (X[m - 1] == Y[n - 1]) {
            return 1 + lcs(X, Y, m - 1, n - 1);
        } else {
            // If not, take the max by excluding one character at a time
            return max(lcs(X, Y, m, n - 1), lcs(X, Y, m - 1, n));
        }
    }

    // Utility function to get maximum of two integers
    static int max(int l1, int l2) {
        return (l1 > l2) ? l1 : l2;
    }

    public static void main(String[] args) {
        Lcs lcs = new Lcs();
        String X = "BDCB";  
        String Y = "BACDB"; 

        char arr1[] = X.toCharArray();
        char arr2[] = Y.toCharArray();

        int len1 = arr1.length;
        int len2 = arr2.length;

        System.out.print("Length of LCS is: " + lcs(arr1, arr2, len1, len2));
    }
}
