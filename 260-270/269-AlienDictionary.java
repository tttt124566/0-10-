/*
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
Example 2:

Input:
[
  "z",
  "x"
]

Output: "zx"
Example 3:

Input:
[
  "z",
  "x",
  "z"
] 

Output: "" 

Explanation: The order is invalid, so return "".
Note:

You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
*/

class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        } 
        if (words.length == 1) {
            return words[0];
        }
        Map<Character, Set<Character>> map = new HashMap<>();
        for (int i = 1; i < words.length; i++) {
            String word1 = words[i - 1];
            String word2 = words[i];
            boolean found = false;
            for (int k = 0; k < word1.length(); k++) {
                map.putIfAbsent(word1.charAt(k), new HashSet<Character>());
            }
            for (int k = 0; k < word2.length(); k++) {
                map.putIfAbsent(word2.charAt(k), new HashSet<Character>());
            }
            for (int k = 0; k < Math.min(word1.length(), word2.length()); k++) {
                if (word1.charAt(k) != word2.charAt(k) && found == false) {
                    map.get(word1.charAt(k)).add(word2.charAt(k));
                    found = true;
                }
            }
        }
        return topologicalSort(map);
    }
    
    private String topologicalSort(Map<Character, Set<Character>> map) {
        Set<Character> visited = new HashSet<Character>();
        Set<Character> onPath = new HashSet<Character>();
        List<Character> res = new ArrayList<Character>();
        StringBuilder sb = new StringBuilder();
        for (Character key : map.keySet()) {
            if (!visited.contains(key)) {
                if (topologicalSortHelper(map, key, visited, onPath, sb)) {
                    return "";
                }
            }
        }
        return sb.reverse().toString();
    }
    
    private boolean topologicalSortHelper(Map<Character, Set<Character>> map, Character c, Set<Character> visited, Set<Character> onPath, StringBuilder sb) {
       if (onPath.contains(c)) {
           return true;
       }
       if (visited.contains(c)) {
           return false;
       }
        
       visited.add(c);
       onPath.add(c);
       if (map.containsKey(c)) {
           for (Character next : map.get(c)) {
               if (topologicalSortHelper(map, next, visited, onPath, sb)) {
                   return true;
               }
           }
       }
       sb.append(c);
       onPath.remove(c);
       return false;
    }
}
