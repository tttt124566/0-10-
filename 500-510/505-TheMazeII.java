/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: 12
Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

Example 2

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: -1
Explanation: There is no way for the ball to stop at the destination.

Note:
There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

*/

class Solution {
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0) {
            return -1;
        }
        int m = maze.length;
        int n = maze[0].length;
        int[][] distance = new int[m][n];
        for (int i = 0; i< m * n ; i++) {
            distance[i / n][i % n] = Integer.MAX_VALUE;
        }
        distance[start[0]][start[1]] = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a, b) -> distance[a[0]][a[1]] - distance[b[0]][b[1]]);
        if (start[0] == destination[0] && start[1] == destination[1]) {
            return 0;
        }
        queue.offer(new int[]{start[0], start[1]});

        
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            if (point[0] == destination[0] && point[1] == destination[1]) {
                return distance[point[0]][point[1]];
            }
            for (int[] dir : dirs) {
                int xx = point[0];
                int yy = point[1];
                int length = distance[xx][yy];
                while (xx >= 0 && yy >= 0 && xx < m && yy < n && maze[xx][yy] != 1) {
                    xx = xx + dir[0];
                    yy = yy + dir[1];
                    length++;
                }
                xx = xx - dir[0];
                yy = yy - dir[1];
                length--;
                if (distance[xx][yy] > length) {
                    distance[xx][yy] = length;
                    queue.offer(new int[]{xx, yy});
                }
            }
        }
        return -1;
    }
}
