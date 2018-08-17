/*
Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

Example 1:

Input: "aabb"
Output: ["abba", "baab"]
Example 2:

Input: "abc"
Output: []
*/

class Solution {
    
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<String>();
        if (!canPermutePalindrome(s)) {
            return res;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        return generatePalindromesHelper(0, s.length() - 1, map);
    }
    
    private List<String> generatePalindromesHelper(int start, int end, Map<Character, Integer> map) {
        if (start > end) {
            List<String> res = new ArrayList<>();
            res.add("");
            return res;
        }
        
        if (start == end) {
            for (Character key : map.keySet()) {
                if (map.get(key) == 1) {
                    List<String> res = new ArrayList<>();
                    res.add(key + "");
                    return res;
                }
            }
        }
        
        List<String> res = new ArrayList<String>();
        
        for (Character key : map.keySet()) {
            if (map.get(key) > 1) {
                map.put(key, map.get(key) - 2);
                List<String> list = generatePalindromesHelper(start + 1, end - 1, map);
                for (String s : list) {
                    res.add(key + s + key);
                }
                map.put(key, map.get(key) + 2);
            }
        }
        
        return res;
    }
    
    private boolean canPermutePalindrome(String s) {
        int[] p = new int[256];
        for (int i = 0; i < s.length(); i++) {
            p[s.charAt(i)]++;
        }
        int oddNums = 0;
        for (int i = 0; i < 256; i++) {
            if (p[i] % 2 == 1) {
                oddNums++;
            }
        }
        return s.length() % 2 == 0 && oddNums == 0 || s.length() % 2 == 1 && oddNums == 1;
    }
}
