/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]

*/

class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        boolean[] p = new boolean[s.length() + 1];
        p[0] = true;
        Set<String> wordSet = new HashSet<String>(wordDict);
        Map<Integer, List<Integer>> wordMap = new HashMap<>();
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 0; j < i; j++) {
                if (p[j] && wordSet.contains(s.substring(j, i))) {
                    p[i] = true;
                    if (!wordMap.containsKey(i)) {
                        wordMap.put(i, new ArrayList<Integer>());
                    }
                    wordMap.get(i).add(j);
                }
            }
        }
        
        List<String> res = new ArrayList<String>();
        wordBreakHelper(res, "", s, s.length(), wordMap);
        return res;
    }
    
    private void wordBreakHelper(List<String> res, String temp, String s, int i, Map<Integer, List<Integer>> wordMap) {
        if (i == 0) {
            res.add(temp.substring(0, temp.length() - 1));
            return;
        }
        
        if (wordMap.containsKey(i)) {
            for (int j : wordMap.get(i)) {
                wordBreakHelper(res, s.substring(j, i) + " " + temp, s, j, wordMap);
            }
        }
    }
}
