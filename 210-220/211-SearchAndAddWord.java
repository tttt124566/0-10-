/*
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

Example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.
*/

class TrieNode {
    char val;
    TrieNode[] children = new TrieNode[26];
    boolean isEnd = false;
    
    TrieNode(char val) {
        this.val = val;
    }
    
    TrieNode() {}
}

public class WordDictionary {
    TrieNode root;

    private boolean searchWordHelper(String word, int i, TrieNode p) {
        if (p == null && i < word.length()) {
            return false;
        }
        
        if (i == word.length()) {
            return p != null && p.isEnd == true;
        }
        
        if (word.charAt(i) == '.') {
            boolean result = false;
            for (TrieNode c : p.children) {
                result = result || searchWordHelper(word, i + 1, c);
            }
            return result;
        } else {
            if (p.children[word.charAt(i) - 'a'] != null) {
                return searchWordHelper(word, i + 1, p.children[word.charAt(i) - 'a']);
            } else {
                return false;
            }
        }
    }
    
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            if (p.children[word.charAt(i) - 'a'] == null) {
                p.children[word.charAt(i) - 'a'] = new TrieNode(word.charAt(i));
            }
            
            p = p.children[word.charAt(i) - 'a'];
        }
        p.isEnd = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        TrieNode p = root;
        return searchWordHelper(word, 0, p);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
