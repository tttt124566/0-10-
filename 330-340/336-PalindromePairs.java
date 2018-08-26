/*
Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:

Input: ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]] 
Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
Example 2:

Input: ["bat","tab","cat"]
Output: [[0,1],[1,0]] 
Explanation: The palindromes are ["battab","tabbat"]
*/

class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        if (words == null || words.length < 2) {
            return res;
        }
        
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j <= words[i].length(); j++) {
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                if (isPalindrome(str1)) {
                    String reverseStr2 = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(reverseStr2) && map.get(reverseStr2) != i) {
                        List<Integer> list = new ArrayList<>();
                        list.add(map.get(reverseStr2));
                        list.add(i);
                        res.add(list);
                    }
                }
                
                if (isPalindrome(str2)) {
                    String reverseStr1 = new StringBuilder(str1).reverse().toString();
                    if (map.containsKey(reverseStr1) && map.get(reverseStr1) != i && !str2.isEmpty()) {
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        list.add(map.get(reverseStr1));
                        res.add(list);
                    }
                }
            }
        }
        
        return res;
    }
    
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
}
