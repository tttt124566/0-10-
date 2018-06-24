/*
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.


*/

class Solution {
    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                solveHelper(board, i, 0);
            }
            
            if (board[i][n - 1] == 'O') {
                solveHelper(board, i, n - 1);
            }
        }
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                solveHelper(board, 0, j);
            }
            if (board[m - 1][j] == 'O') {
                solveHelper(board, m - 1, j);
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    private void solveHelper(char[][] board, int i, int j) {
        Queue<Point> queue = new LinkedList<Point>();
        board[i][j] = '#';
        queue.offer(new Point(i, j));
        while (!queue.isEmpty()) {
            Point front = queue.poll();
            for (int[] dir : dirs ) {
                int xx = front.x + dir[0];
                int yy = front.y + dir[1];
                if (xx >= 0 && xx < board.length && yy >= 0 && yy < board[0].length && board[xx][yy] == 'O') {
                    board[xx][yy] = '#';
                    queue.offer(new Point(xx, yy));
                }
            }
        }
    }
}
