/*

Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]

*/

class Solution {
    private boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        List<String> path = new ArrayList<String>();
        
        partitionHelper(s, 0, path, res);
        return res;
    }
    
    private void partitionHelper(String s, int i, List<String> path, List<List<String>> res) {
        if (i == s.length()) {
            res.add(new ArrayList<String>(path));
            return;
        }
        
        for (int j = i; j < s.length(); j++) {
            if (isPalindrome(s.substring(i, j + 1))) {
                path.add(s.substring(i, j + 1));
                partitionHelper(s, j + 1, path, res);
                path.remove(path.size() - 1);
            }
        }
    }
}
