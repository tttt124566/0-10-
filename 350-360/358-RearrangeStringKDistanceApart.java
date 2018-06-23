/*
Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:
s = "aabbcc", k = 3

Result: "abcabc"

The same letters are at least distance 3 from each other.
Example 2:
s = "aaabc", k = 3 

Answer: ""

It is not possible to rearrange the string.
Example 3:
s = "aaadbbcc", k = 2

Answer: "abacabcd"

Another possible answer is: "abcabcda"

The same letters are at least distance 2 from each other.

*/

class Solution {
    public String rearrangeString(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);  // char freq
        }
        Queue<Map.Entry<Character, Integer>> q = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue()); // PriorityQueue, order by freq
        q.addAll(map.entrySet());
        StringBuilder sb = new StringBuilder();
        Queue<Map.Entry<Character, Integer>> waitList = new LinkedList();
        while (!q.isEmpty()) { // q hold all currently available (ch, freq) pairs, all non-valid ones r in waitList
            Map.Entry<Character, Integer> front = q.poll();
            sb.append(front.getKey());
            front.setValue(front.getValue() - 1); // need to set the value - 1 for map entry. and then push to maxHeap later.
            waitList.add(front);
            if (waitList.size() >= k) {
                Map.Entry<Character, Integer> now = waitList.poll();
                if (now.getValue() > 0) {
                    q.add(now);
                }
            }
        }
        return (sb.length() == s.length()) ? sb.toString() : ""; // (ch, freq) stuck in waitList
    }
}
