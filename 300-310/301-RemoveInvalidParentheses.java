/*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]

*/
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int rmL = 0;
        int rmR = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                rmL++;
            } else {
                if (s.charAt(i) == ')') {
                    if (rmL > 0) {
                        rmL--;
                    } else {
                        rmR++;
                    }
                }
            }
        }
        List<String> res = new ArrayList<String>();
        Set<String> set = new HashSet<>();
        dfs(s, 0, set, new StringBuilder(), rmL, rmR, 0);
        
        for (String s: set) {
            res.add(s);
        }
        return res;
    }

    public void dfs(String s, int i, Set<String> res, StringBuilder sb, int rmL, int rmR, int open) {
        if (rmL < 0 || rmR < 0 || open < 0) {
            return;
        }
        if (i == s.length()) {
            if (rmL == 0 && rmR == 0 && open == 0) {
                res.add(sb.toString());
            }        
            return;
        }

        char c = s.charAt(i); 
        int len = sb.length();

        if (c == '(') {
            dfs(s, i + 1, res, sb, rmL - 1, rmR, open);		    // not use (
            dfs(s, i + 1, res, sb.append(c), rmL, rmR, open + 1);       // use (
        } else if (c == ')') {
            dfs(s, i + 1, res, sb, rmL, rmR - 1, open);	            // not use  )
            dfs(s, i + 1, res, sb.append(c), rmL, rmR, open - 1);  	    // use )
        } else {
            dfs(s, i + 1, res, sb.append(c), rmL, rmR, open);	
        }

        sb.setLength(len);        
    }
}
