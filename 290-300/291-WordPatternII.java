/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Example 1:

Input: pattern = "abab", str = "redblueredblue"
Output: true
Example 2:

Input: pattern = pattern = "aaaa", str = "asdasdasdasd"
Output: true
Example 3:

Input: pattern = "aabb", str = "xyzabcxzyabc"
Output: false
Notes:
You may assume both pattern and str contains only lowercase letters.

*/

class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        return wordPatternMatchHelper(pattern, str, 0, 0, map);
    }
    
    private boolean wordPatternMatchHelper(String pattern, String str, int i, int j, Map<Character, String> map) {
        if (i == pattern.length() || j == str.length()) {
            if (i == pattern.length() && j == str.length()) {
                return true;
            } else {
                return false;
            }
        }
        
        if (!map.containsKey(pattern.charAt(i))) {
            boolean res = false;
            for (int k = j + 1; k <= str.length(); k++) {
                if (map.containsValue(str.substring(j, k))) {
                    continue;
                }
                map.put(pattern.charAt(i), str.substring(j, k));
                res = res || wordPatternMatchHelper(pattern, str, i + 1, k, map);
                map.remove(pattern.charAt(i));
            }
            return res;
        } else {
            String s = map.get(pattern.charAt(i));
            if (s.length() > str.substring(j).length()) {
                return false;
            }
            if (s.equals(str.substring(j, j + s.length()))) {
                return wordPatternMatchHelper(pattern, str, i + 1, j + s.length(), map);
            }
            return false;
        }
    }
}
