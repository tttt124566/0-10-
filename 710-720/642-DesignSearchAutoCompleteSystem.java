/*
Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:

The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
If less than 3 hot sentences exist, then just return as many as you can.
When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
Your job is to implement the following functions:

The constructor function:

AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical data.

Now, the user wants to input a new sentence. The following function will provide the next character the user types:

List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.


Example:
Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2]) 
The system have already tracked down the following sentences and their corresponding times: 
"i love you" : 5 times 
"island" : 3 times 
"ironman" : 2 times 
"i love leetcode" : 2 times 
Now, the user begins another search: 

Operation: input('i') 
Output: ["i love you", "island","i love leetcode"] 
Explanation: 
There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored. 

Operation: input(' ') 
Output: ["i love you","i love leetcode"] 
Explanation: 
There are only two sentences that have prefix "i ". 

Operation: input('a') 
Output: [] 
Explanation: 
There are no sentences that have prefix "i a". 

Operation: input('#') 
Output: [] 
Explanation: 
The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search. 

Note:
The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
The number of complete sentences that to be searched won't exceed 100. The length of each sentence including those in the historical data won't exceed 100.
Please use double-quote instead of single-quote when you write test cases even for a character input.
Please remember to RESET your class variables declared in class AutocompleteSystem, as static/class variables are persisted across multiple test cases. Please see here for more details.
*/
/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */

class Trie {
    private Map<String, Integer> map;
    TrieNode root;
    
    class TrieNode {
        public char val;
        public Map<Character, TrieNode> children = new HashMap<>();
        public boolean isEnd = false;
        public PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            if (map.getOrDefault(a, 0) == map.getOrDefault(b, 0)) {
                return b.compareTo(a);
            } else {
                return map.getOrDefault(a, 0) - map.getOrDefault(b, 0);
            }
        });
        TrieNode() {}
        TrieNode(char val) {
            this.val = val;
        }
    }

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
        map = new HashMap<>();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word, int time) {
        map.putIfAbsent(word, 0);
        map.put(word, map.get(word) + time);
        
        TrieNode p = root;
        
        for (int i = 0; i < word.length(); i++) {
            if (!p.children.containsKey(word.charAt(i))) {
                p.children.put(word.charAt(i), new TrieNode(word.charAt(i)));
            }
            p = p.children.get(word.charAt(i));
                           
            // it seems we have to remove then add otherwise priority queue won't get updated.
            p.pq.remove(word);
            p.pq.add(word);
            if (p.pq.size() > 3) {
                p.pq.poll();
            }
        }
        
        p.isEnd = true;
    }

    public List<String> startsWith(String prefix) {
        TrieNode p = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (!p.children.containsKey(prefix.charAt(i))) {
                return new ArrayList<>();
            }
            p = p.children.get(prefix.charAt(i));
        }
        List<String> res = new ArrayList<>();
        PriorityQueue<String> copy = new PriorityQueue<>(p.pq);
        while (!copy.isEmpty()) {
            res.add(0, copy.poll());
        }
        return res;
    }
}

public class AutocompleteSystem {

    StringBuilder sb; 
    List<String> res;
    Trie trie;
    public AutocompleteSystem(String[] sentences, int[] times) {
        trie = new Trie();
        for (int i = 0; i < sentences.length; i++) {
            trie.insert(sentences[i], times[i]);
        }
        sb = new StringBuilder();
        res = new ArrayList<>();
    }
    
    public List<String> input(char c) {
        if (c == '#') {
            trie.insert(sb.toString(), 1);
            sb.setLength(0);
            List<String> copy = new ArrayList<>(res);
            res.clear();
            return copy;
        }
        sb.append(c);
        return trie.startsWith(sb.toString());
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */

