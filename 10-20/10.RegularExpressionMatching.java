class Solution {
    public boolean isMatch(String s, String p) {
        /**
         * f[i][j]: if s[0..i-1] matches p[0..j-1]
         * if p[j - 1] != '*'
         *      f[i][j] = f[i - 1][j - 1] && s[i - 1] == p[j - 1]
         * if p[j - 1] == '*', denote p[j - 2] with x
         *      f[i][j] is true iff any of the following is true
         *      1) "x*" repeats 0 time and matches empty: f[i][j - 2]
         *      2) "x*" repeats >= 1 times and matches "x*x": s[i - 1] == x && f[i - 1][j]
         * '.' matches any single character
         */
        
        if (s == null && p == null) {
            return true;
        }
        boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
        match[0][0] = true;
        // initialization
        for (int i = 1; i < s.length() + 1; i++) {
            match[i][0] = false;
        }
        for (int j = 2; j < p.length() + 1; j++) {
            match[0][j] = (p.charAt(j - 1) == '*') && match[0][j - 2];
        }
        
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < p.length() + 1; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    match[i][j] = match[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    match[i][j] = match[i][j - 2] || (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') && match[i - 1][j];
                }
            }
        }
        return match[s.length()][p.length()];
    }
}
