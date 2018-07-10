/*
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

Example:

Input: 
words = ["oath","pea","eat","rain"] and board =
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]

Output: ["eat","oath"]
Note:
You may assume that all inputs are consist of lowercase letters a-z.
*/
class TrieNode {
    char c;
    boolean isEnd = false;
    TrieNode[] children = new TrieNode[26];
    TrieNode() {}
    TrieNode(char t) {
        c = t;
    }
}

class Trie {
    private TrieNode root;
    
    public TrieNode getRoot() {
        return root;
    }
    
    Trie() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode p = root;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i++) {
            if (p.children[words[i] - 'a'] == null) {
                p.children[words[i] - 'a'] = new TrieNode(words[i]);
            }
            p = p.children[words[i] - 'a'];
        }
        p.isEnd = true;
    }
    
    public void addWords(String[] words) {
        for (String word : words) {
            addWord(word);
        }
    }
}

public class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        trie.addWords(words);
        Set<String> wordSet = new HashSet<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                findWordsHelper(trie.getRoot(), i, j, board, new StringBuilder(), wordSet);
            }
        }
        for (String word : wordSet) {
            list.add(word);
        }
        return list;
    }
    
    private void findWordsHelper(TrieNode root, int i, int j, char[][] board, StringBuilder sb, Set<String> wordSet) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == '.') {
            return;
        }
        if (root.children[board[i][j] - 'a'] != null) {
            char tmp = board[i][j];
            sb.append(tmp);
            TrieNode p = root.children[board[i][j] - 'a'];
            if (p.isEnd) {
                wordSet.add(sb.toString());
                p.isEnd = false;
            }
            board[i][j] = '.';
            findWordsHelper(p, i + 1, j, board, sb, wordSet);
            findWordsHelper(p, i, j + 1, board, sb, wordSet);
            findWordsHelper(p, i - 1, j, board, sb, wordSet);
            findWordsHelper(p, i, j - 1, board, sb, wordSet);
            board[i][j] = tmp;
            sb.setLength(sb.length() - 1);
        }
    }
}
