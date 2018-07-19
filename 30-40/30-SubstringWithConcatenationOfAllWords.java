/*
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

Example 1:

Input:
  s = "barfoothefoobarman",
  words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.
Example 2:

Input:
  s = "wordgoodstudentgoodword",
  words = ["word","student"]
Output: []

*/

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words.length == 0) {
            return res;
        }
        
        int wordLength = words[0].length();
        int wordsSize = words.length;
        
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        
        
        for (int i = 0; i < s.length() - wordLength * wordsSize + 1; i++) {
            Map<String, Integer> map2 = new HashMap<>();
            int count = 0;
            for (int j = i; j < i + wordsSize * wordLength; j += wordLength) {
                String temp = s.substring(j, j + wordLength);
                if (!map.containsKey(temp)) {
                    break;
                } else {
                    map2.put(temp, map2.getOrDefault(temp, 0) + 1);
                    if (map2.get(temp) > map.get(temp)) {
                        break;
                    }
                    count++;
                } 
            }
            if (count == words.length) {
                res.add(i);
            }
        }
        
        return res;
    }
}
