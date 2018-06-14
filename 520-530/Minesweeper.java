class Solution {
    
    private int[][] dirs;
    public char[][] updateBoard(char[][] board, int[] click) {
        dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, -1}, {-1, 1}, {1, 1}, {-1, -1}};
        updateBoardHelper(board, click[0], click[1]);
        return board;
    }
    
    private void updateBoardHelper(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }
        if (board[i][j] == 'M') {
            board[i][j] = 'X';
            return;
        }
        if (board[i][j] == 'E') {
            int minesNum = countMines(board, i, j);
            if(minesNum == 0) {
                board[i][j] = 'B';
                for (int m = 0; m < dirs.length; m++) {
                    updateBoardHelper(board, i + dirs[m][0], j + dirs[m][1]);
                }
            } else {
                board[i][j] = (char)(minesNum + '0'); // be careful how to convert 1 to '1'
            }
        }
    }
    
    private int countMines(char[][] board, int i, int j) {
        int count = 0;
        for (int t = Math.max(0, i - 1); t <= Math.min(board.length - 1, i + 1); t++) {
            for (int k = Math.max(0, j - 1); k <= Math.min(board[0].length - 1, j + 1); k++) {
                if (t == i && k == j) {
                    continue;
                }
                if (board[t][k] == 'M') {
                    count++;
                }
            }
        }
        return count;
    }
}
