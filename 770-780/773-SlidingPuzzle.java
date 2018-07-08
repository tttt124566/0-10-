/*
On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

Examples:

Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.
Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.
Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]
Input: board = [[3,2,4],[1,5,0]]
Output: 14
*/

class Solution {
    public int slidingPuzzle(int[][] board) {
        Queue<BoardState> queue = new ArrayDeque<>();
        Set<BoardState> visited = new HashSet<>();
        BoardState boardState = new BoardState(board);
        queue.add(boardState);
        visited.add(boardState);
        while (!queue.isEmpty()) {
            BoardState first = queue.poll();
            if (first.isWin()) {
                return first.getLevel();
            }
            for (BoardState next: first.getNextStatesAfterSliding()) {
                if (visited.contains(next)) {
                    continue;
                }
                visited.add(next);
                queue.add(next);
            }
        }
        
        return -1;
    }
    
    static int[][] deepCopy(int[][] board) {
        int[][] copy = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            copy[i] = board[i].clone();
        }
        return copy;
    }
        
    private class BoardState {
        int[][] board;
        int zx;
        int zy;
        int level;
        
        BoardState(int[][] board) {
            this.board = board;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 0) {
                        this.zx = i;
                        this.zy = j;
                    }
                }
            }
            this.level = 0;
        }
        
        BoardState(BoardState parent) {
            this.board = deepCopy(parent.board);
            this.zx = parent.zx;
            this.zy = parent.zy;
            this.level = parent.level + 1;
        }
    
        public int getLevel() {
            return this.level;
        }
        
        public List<BoardState> getNextStatesAfterSliding() {
            List<BoardState> res = new ArrayList<>();
            if (zx > 0) {
                res.add(new BoardState(this).swapZero(zx - 1, zy));
            }
            if (zy > 0) {
                res.add(new BoardState(this).swapZero(zx, zy - 1));
            }
            if (zx < board.length - 1) {
                res.add(new BoardState(this).swapZero(zx + 1, zy));
            }
            if (zy < board[0].length - 1) {
                res.add(new BoardState(this).swapZero(zx, zy + 1));
            }
            return res;
        }
        
        private BoardState swapZero(int x, int y) {
            int temp = board[zx][zy];
            this.board[zx][zy] = board[x][y];
            board[x][y] = temp;
            zx = x;
            zy = y;
            return this;
        }
        
        public boolean isWin() {
            return board[0][0] == 1 &&
                   board[0][1] == 2 &&
                    board[0][2] == 3 &&
                    board[1][0] == 4 &&
                    board[1][1] == 5;
        }

        
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            
            if (o == null || o.getClass() != getClass()) {
                return false;
            }
            
            BoardState that = (BoardState) o;
            return Arrays.deepEquals(board, that.board);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(board);
        }

        @Override
        public String toString() {
            return Arrays.deepToString(board);
        }
    }
}

/*
import java.util.*;

class SlidingGame {.本文原创自1point3acres论坛
    private int[] dx = {-1, 0, 1, 0};
    private int[] dy = {0, -1, 0, 1};. more info on 1point3acres

    class Status {
        int[][] matrix;
        int x, y;
        Status(int[][] m, int i, int j) {
            matrix = new int[m.length][m[0].length];. 牛人云集,一亩三分地
            for (int ii = 0; ii < m.length; ii++)
                matrix[ii] = m[ii].clone();
            x = i;
            y = j;-google 1point3acres
        }
        String encodeMatrix() {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < matrix.length; i++)
                for (int j = 0; j < matrix.length; j++)
                    builder.append(matrix[j]).append(",");
            return builder.toString();
        }
        void move(int i, int j) {
            matrix[x][y] = matrix[j];.1point3acres网
            matrix[j] = 0;
            x = i;
            y = j;. 牛人云集,一亩三分地
        }
    }

    public boolean canSolve(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
来源一亩.三分地论坛. 
        int startX = 0, startY = 0;
        int[][] finalMatrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                finalMatrix[j] = i * m + j;
                if (matrix[j] == 0) {
                    startX = i;
                    startY = j;
                }
            }.留学论坛-一亩-三分地
        }

        Status initial = new Status(matrix, startX, startY);. from: 1point3acres 
        Status finals = new Status(finalMatrix, 0, 0);
        Queue<Status> queue = new LinkedList<>();. visit 1point3acres for more.
        queue.add(initial);. 1point3acres
        Set<String> visited = new HashSet<>();
        visited.add(initial.encodeMatrix());
        System.out.println(initial.encodeMatrix());

        while (!queue.isEmpty()) {
            Status current = queue.poll();
            System.out.println("F: " + current.encodeMatrix());
            int xx = current.x, yy = current.y;
            for (int i = 0; i < 4; i++) {
                int x = xx + dx; 来源一亩.三分地论坛. 
                int y = yy + dy;
                if (x < 0 || x >= m || y < 0 || y >= n)
                    continue;
                current.move(x, y);
                if (current.encodeMatrix().equals(finals.encodeMatrix()))
                    return true;
                if (!visited.contains(current.encodeMatrix())) {
                    visited.add(current.encodeMatrix());
                    System.out.println(current.encodeMatrix());
                    queue.add(new Status(current.matrix, current.x, current.y));
                }
                current.move(xx, yy);
            }
        }

        return false;
    }


    public final static void main(String[] args) {
        SlidingGame sg = new SlidingGame();
        int[][] matrix = new int[][]{{2, 3, 8}, {1, 4, 7}, {6, 0, 5}};. Waral 博客有更多文章,
        System.out.println(sg.canSolve(matrix));
    }

}
*/
