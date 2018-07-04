/*
Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y.

For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".

Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.

We are given a list A of strings.  Every string in A is an anagram of every other string in A.  How many groups are there?

Example 1:

Input: ["tars","rats","arts","star"]
Output: 2
Note:

A.length <= 2000
A[i].length <= 1000
A.length * A[i].length <= 20000
All words in A consist of lowercase letters only.
All words in A have the same length and are anagrams of each other.
The judging time limit has been increased for this question.

*/

public class Solution {
    
    private boolean similar(String s, String t) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                count++;
            }
        }
        return count <= 2;
    }
    
    public int numSimilarGroups(String[] A) {
        UF uf = new UF(A.length);
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (similar(A[i], A[j])) {
                    uf.union(i, j);
                }
            }
        }
        
       return uf.getDifference();
    }
}

class UF {
    private int[] nums;
    private int n;
    
    UF(int n) {
        nums = new int[n];
        this.n = n;
        for (int i = 0; i < n; i++) {
            nums[i] = i;
        }
    }
    
    public int find(int i) {
        while (nums[i] != i) {
            nums[i] = nums[nums[i]];
            i = nums[i];
        }
        return i;
    }
    
    public void union(int i, int j) {
        int iRoot = find(i);
        int jRoot = find(j);
        if (iRoot != jRoot) {
            nums[iRoot] = jRoot;
        }
    }
    
    public int getDifference() {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i) {
                count++;
            }
        }
        return count;
    }
}
