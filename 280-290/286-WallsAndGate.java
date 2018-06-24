/*
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

Example: 

Given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4

*/

class Solution {
    int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }
        Queue<int[]> queue = new LinkedList<int[]>();
        int m = rooms.length;
        int n = rooms[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        
        while(!queue.isEmpty()) {
            int[] front = queue.poll();
            for (int[] dir : dirs) {
                int x = dir[0] + front[0];
                int y = dir[1] + front[1];
                if (x >= 0 && y >= 0 && x < m && y < n && rooms[x][y] != -1 && rooms[x][y] > rooms[front[0]][front[1]] + 1) {
                    rooms[x][y] = rooms[front[0]][front[1]] + 1;
                    queue.offer(new int[]{x, y});
                }
            }
        }
    }
}

// DFS

class Solution {
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    wallsAndGatesHelper(rooms, i, j, 0);
                }
            }
        }
    }
    
    private void wallsAndGatesHelper(int[][] rooms, int i, int j, int l) {
        if (i < 0 || j < 0 || i >= rooms.length || j >= rooms[0].length || rooms[i][j] == -1 || rooms[i][j] < l) {
            return;
        }
        rooms[i][j] = l;
        for (int[] dir : dirs) {
            wallsAndGatesHelper(rooms, i + dir[0], j + dir[1], l + 1);
        }
    }
}
