/*
Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].

*/

class Solution {
    public String reorganizeString(String S) {
        Map<Character, Integer> map = new HashMap<>();
        
        for (int i = 0; i < S.length(); i++) {
            map.put(S.charAt(i), map.getOrDefault(S.charAt(i), 0) + 1);
        }
        
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            pq.add(entry);
        }
        
        StringBuilder sb = new StringBuilder();
                
        Queue<Map.Entry<Character, Integer>> queue = new LinkedList<>();
        
        while(!pq.isEmpty()) {
            Map.Entry<Character, Integer> first = pq.poll();
            first.setValue(first.getValue() - 1);
            sb.append(first.getKey());
            queue.add(first);
            
            if (queue.size() >= 2) {
                Map.Entry<Character, Integer> temp = queue.poll();
                if (temp.getValue() > 0) {
                    pq.add(temp);
                }
            }
        }
        
        return sb.toString().length() == S.length() ? sb.toString() : "";
    }
}
