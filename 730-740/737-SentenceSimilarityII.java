/*
Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.

Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

Note:

The length of words1 and words2 will not exceed 1000.
The length of pairs will not exceed 2000.
The length of each pairs[i] will be 2.
The length of each words[i] and pairs[i][j] will be in the range [1, 20].
*/

class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        Map<String, Integer> map = new HashMap<>();
        constructMap(pairs, map);
        UF uf = new UF(map.size());
        constructUF(pairs, map, uf);
        
        for (int i = 0; i < words1.length; i++) {
            String w1 = words1[i];
            String w2 = words2[i];
            if (!map.containsKey(w1) || !map.containsKey(w2)) {
                if (!w1.equals(w2)) {
                    return false;
                }
                continue;
            }
            int i1 = map.get(w1);
            int i2 = map.get(w2);
            if (uf.find(i1) != uf.find(i2)) {
                return false;
            }
        }
        return true;
    }
    
    private void constructMap(String[][] pairs, Map<String, Integer> map) {
        int mi = 0;
        for (String[] pair : pairs) {
            if (!map.containsKey(pair[0])) {
                map.put(pair[0], mi++);
            }
            if (!map.containsKey(pair[1])) {
                map.put(pair[1], mi++);
            }
        }
    }

    private void constructUF(String[][] pairs, Map<String, Integer> map, UF uf) {
        for (String[] pair : pairs) {
            int a = map.get(pair[0]);
            int b = map.get(pair[1]);
            uf.union(a, b);
        }
    }
}

class UF {
    int[] parent;
    
    UF(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }
    
    public void union(int a, int b) {
        int aparent = find(a);
        int bparent = find(b);
        parent[aparent] = bparent;
    }
    
    // path compression
    public int find(int a) {
        while (a != parent[a]) {
            parent[a] = parent[parent[a]];
            a = parent[a];
        }
        return a;
    }
}
