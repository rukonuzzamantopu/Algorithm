public class SecondLongestLCS {

    // Basic recursive function to calculate LCS length
    public static int lcs(String X, String Y, int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (X.charAt(m - 1) == Y.charAt(n - 1))
            return 1 + lcs(X, Y, m - 1, n - 1);
        else
            return Math.max(lcs(X, Y, m - 1, n), lcs(X, Y, m, n - 1));
    }

    // Modified version: try skipping one match to find second best LCS
    public static int secondLongestLCS(String X, String Y) {
        int maxLen = lcs(X, Y, X.length(), Y.length());
        int secondMax = 0;

        // Try removing each character one-by-one from X or Y and check new LCS
        for (int i = 0; i < X.length(); i++) {
            String newX = X.substring(0, i) + X.substring(i + 1);
            int newLen = lcs(newX, Y, newX.length(), Y.length());
            if (newLen < maxLen)
                secondMax = Math.max(secondMax, newLen);
        }

        for (int i = 0; i < Y.length(); i++) {
            String newY = Y.substring(0, i) + Y.substring(i + 1);
            int newLen = lcs(X, newY, X.length(), newY.length());
            if (newLen < maxLen)
                secondMax = Math.max(secondMax, newLen);
        }

        return secondMax;
    }

    public static void main(String[] args) {
        String X = "BDCB";
        String Y = "BACDB";

        int result = secondLongestLCS(X, Y);
        System.out.println("Length of Second Longest LCS is: " + result);
    }
}
