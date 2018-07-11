/*
Given a set of keywords words and a string S, make all appearances of all keywords in S bold. Any letters between <b> and </b> tags become bold.

The returned string should use the least number of tags possible, and of course the tags should form a valid combination.

For example, given that words = ["ab", "bc"] and S = "aabcd", we should return "a<b>abc</b>d". Note that returning "a<b>a<b>b</b>c</b>d" would use more tags, so it is incorrect.

Note:

words has length in range [0, 50].
words[i] has length in range [1, 10].
S has length in range [0, 500].
All characters in words[i] and S are lowercase letters.
*/

class Solution {
    public String boldWords(String[] words, String S) {
        boolean[] bold = new boolean[S.length()];
        for (String word: words) {
            int i = 0;
            while (i < S.length()) {
                i = S.indexOf(word, i);
                if (i == -1) {
                    break;
                }
                for (int j = i; j < i + word.length(); j++) {
                    bold[j] = true;
                }
                i = i + 1;
            }
        }
        
        for (int i = 0; i < bold.length; i++) {
            System.out.print(bold[i]+",");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if (bold[i] && (i == 0 || !bold[i - 1])) {
                sb.append("<b>");
            } 
            sb.append(S.charAt(i));
            if (bold[i] && ( i == S.length() - 1 || !bold[i + 1])) {
                sb.append("</b>");
            }
        }
        return sb.toString();
    }
}
