public class commonSubsequences {

    static String[] results = new String[1000];  
    static int count = 0;

    public static void findCommonSubseq(char X[], char Y[], int m, int n, String current) {
        if (m == 0 || n == 0) {
            if (!current.equals("")) {
                addIfNotExists(current);
            }
            return;
        }

        if (X[m - 1] == Y[n - 1]) {
            findCommonSubseq(X, Y, m - 1, n - 1, X[m - 1] + current);
        } else {
            findCommonSubseq(X, Y, m - 1, n, current);
            findCommonSubseq(X, Y, m, n - 1, current);
        }
    }

    
    public static void addIfNotExists(String s) {
        for (int i = 0; i < count; i++) {
            if (results[i].equals(s)) {
                return;
            }
        }
        results[count++] = s;
    }

    
    public static void sort() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                if (results[i].length() < results[j].length()) {
                    String temp = results[i];
                    results[i] = results[j];
                    results[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        String X = "XYZK";
        String Y = "YZKX";

        char arr1[] = X.toCharArray();
        char arr2[] = Y.toCharArray();

        int len1 = arr1.length;
        int len2 = arr2.length;

        findCommonSubseq(arr1, arr2, len1, len2, "");

        sort();

        System.out.println("Common subsequences (sorted by descending length):");
        for (int i = 0; i < count; i++) {
            System.out.println(results[i]);
        }
    }
}
