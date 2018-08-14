/*
Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters. 

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1
Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.


*/

class WordDistance {

    Map<String, List<Integer>> map;
    public WordDistance(String[] words) {
        map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.computeIfAbsent(words[i], k -> new ArrayList<Integer>()).add(i);
        }
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> l1 = map.getOrDefault(word1, new ArrayList<Integer>());
        List<Integer> l2 = map.getOrDefault(word2, new ArrayList<Integer>());
        
        int i = 0;
        int j = 0;
        int minLength = Integer.MAX_VALUE;
        while (i < l1.size() && j < l2.size()) {
            int index1 = l1.get(i);
            int index2 = l2.get(j);
            if (index1 < index2) {
                minLength = Math.min(index2 - index1, minLength);
                i++;
            } else {
                minLength = Math.min(index1 - index2, minLength);
                j++;
            }
        }
        return minLength;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */
