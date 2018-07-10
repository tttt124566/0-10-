/*
Strings A and B are K-similar (for some non-negative integer K) if we can swap the positions of two letters in A exactly K times so that the resulting string equals B.

Given two anagrams A and B, return the smallest K for which A and B are K-similar.

Example 1:

Input: A = "ab", B = "ba"
Output: 1
Example 2:

Input: A = "abc", B = "bca"
Output: 2
Example 3:

Input: A = "abac", B = "baca"
Output: 2
Example 4:

Input: A = "aabc", B = "abca"
Output: 2
Note:

1 <= A.length == B.length <= 20
A and B contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}

*/

class Solution {
    public int kSimilarity(String A, String B) {
        if (A.equals(B)) {
            return 0;
        }
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int res = 0;
        q.add(A);
        visited.add(A);
        while (!q.isEmpty()) {
            int size = q.size();
            res++;
            while (size > 0) {
                String first = q.poll();
                size--;
                int i = 0;
                while (first.charAt(i) == B.charAt(i)) {
                    i++;
                }
                for (int j = i + 1; j < B.length(); j++) {
                    if ((first.charAt(i) == B.charAt(j)) && (first.charAt(j) != B.charAt(j))) {
                        String swaped = swap(first, i, j);
                        if (swaped.equals(B)) {
                            return res;
                        }
                        if (visited.contains(swaped)) {
                            continue;
                        }
                        q.add(swaped);
                        visited.add(swaped);
                    }
                }
            }
        }
        return res;
    }
    
    public String swap(String s, int i, int j){
        char[] ca=s.toCharArray();
        char temp=ca[i];
        ca[i]=ca[j];
        ca[j]=temp;
        return new String(ca);
    }
}
