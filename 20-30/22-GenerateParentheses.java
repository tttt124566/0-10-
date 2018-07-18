/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

*/

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateParenthesisHelper(res, n, n, "");
        return res;
    }
    
    private void generateParenthesisHelper(List<String> res, int i, int j, String s) {
        if (i > j) {
            return;
        }
        if (i == 0 && j == 0) {
            res.add(s);
            return;
        }
        if (i >= 0) {
            generateParenthesisHelper(res, i - 1, j, s + '(');
        }
        if (j >= 0) {
            generateParenthesisHelper(res, i, j - 1, s + ')');
        }
    }
}
