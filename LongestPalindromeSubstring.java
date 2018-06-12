class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];
        // initialization
        for (int i = 0; i < s.length(); i++) {
            isPalindrome[i][i] = true;
        }
        int start = 0;
        int maxLength = 1; // be careful, maxLength == 1
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j < 2 || isPalindrome[i - 1][j + 1])) {
                    isPalindrome[i][j] = true;
                    if (i - j + 1 > maxLength) {
                        maxLength = i - j + 1;
                        start = j;
                    }
                }
            }
        }
        return s.substring(start, start + maxLength);
    }
}
