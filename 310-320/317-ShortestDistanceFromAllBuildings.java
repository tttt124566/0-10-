/*
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
Example:

Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 7 

Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
             the point (1,2) is an ideal empty land to build a house, as the total 
             travel distance of 3+3+1=7 is minimal. So return 7.

*/

class Solution {
    private int[][] dirs = {{ 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }};
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] distance = new int[m][n];
        int[][] buildingMap = new int[m][n];
        
        int buildingNum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    buildingNum++;
                    Queue<Point> queue = new LinkedList<Point>();
                    boolean[][] visited = new boolean[m][n];
                    queue.offer(new Point(i, j));
                    int level = 1;
                    while (!queue.isEmpty()) {
                        int queueSize = queue.size();
                        for (int k = 0; k < queueSize; k++) {
                            Point front = queue.poll();
                            int x = front.x;
                            int y = front.y;
                            for (int[] dir : dirs) {
                                int xx = x + dir[0];
                                int yy = y + dir[1];
                                if (xx < m && yy < n && xx >= 0 && yy >= 0 && grid[xx][yy] == 0 && !visited[xx][yy]) {
                                    distance[xx][yy] += level;
                                    visited[xx][yy] = true;
                                    buildingMap[xx][yy] += 1;
                                    queue.offer(new Point(xx, yy));
                                }
                            }
                        }
                        level++;
                    }
                }
            }
        }
        
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (buildingMap[i][j] == buildingNum) {
                    result = Math.min(distance[i][j], result);
                }
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result; // be careful to return -1 when there is no satisfiying point.
    }
}
