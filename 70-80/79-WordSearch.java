/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
*/

class Solution {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int m = board.length;
                int n = board[0].length;
                if (existHelper(board, i, j, word, 0, new boolean[m][n])) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean existHelper(char[][] board, int i, int j, String word, int t, boolean[][] visited) {
        if (t == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]) {
            return false;
        }
        if (board[i][j] != word.charAt(t)) {
            return false;
        }
        visited[i][j] = true;
        boolean exist = false;
        for (int[] dir : dirs) {
            exist = exist || existHelper(board, i + dir[0], j + dir[1], word, t + 1, visited);
        }
        visited[i][j] = false;
        return exist;
    }
}
