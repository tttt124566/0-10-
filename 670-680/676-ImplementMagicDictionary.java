/*
Implement a magic directory with buildDict, and search methods.

For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.

For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word, the modified word is in the dictionary you just built.

Example 1:
Input: buildDict(["hello", "leetcode"]), Output: Null
Input: search("hello"), Output: False
Input: search("hhllo"), Output: True
Input: search("hell"), Output: False
Input: search("leetcoded"), Output: False
Note:
You may assume that all the inputs are consist of lowercase letters a-z.
For contest purpose, the test data is rather small by now. You could think about highly efficient algorithm after the contest.
Please remember to RESET your class variables declared in class MagicDictionary, as static/class variables are persisted across multiple test cases. Please see here for more details.
*/

class TrieNode {
    char var;
    boolean isEnd = false;
    TrieNode[] children = new TrieNode[26];
    TrieNode() {}
    TrieNode(char c) {
        var = c;
    }
}

class Trie {
    private TrieNode root;
    
    Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            if (p.children[word.charAt(i) - 'a'] == null) {
                p.children[word.charAt(i) - 'a'] = new TrieNode(word.charAt(i));
            }
            p = p.children[word.charAt(i) - 'a'];
        }
        p.isEnd = true;
    }
    
    public TrieNode getRoot() {
        return root;
    }
}

public class MagicDictionary {

    /** Initialize your data structure here. */
    private Trie trie;
    public MagicDictionary() {
        trie = new Trie();
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String word : dict) {
            trie.insert(word);
        }
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        TrieNode p = trie.getRoot();
        return searchHelper(word.toCharArray(), 0, p, false);
    }
    
    private boolean searchHelper(char[] words, int i, TrieNode p, boolean hasFoundOne) {
        if (i == words.length) {
            return p.isEnd && hasFoundOne;
        }
        if (!hasFoundOne) {
            for (int j = 0; j < p.children.length; j++) {
                boolean found = false;
                if (p.children[j] != null) {
                    if (j != words[i] - 'a') {
                        found = searchHelper(words, i + 1, p.children[j], true);
                    } else {
                        found = searchHelper(words, i + 1, p.children[j], false);
                    }
                }
                
                if (found) {
                    return true;
                }
            }
        } else if (hasFoundOne) {
            if (p.children[words[i] - 'a'] == null) {
                return false;
            }
            return searchHelper(words, i + 1, p.children[words[i] - 'a'], true);
        }
        
        return false;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */
