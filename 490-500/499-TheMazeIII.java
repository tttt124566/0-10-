/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.

Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (0, 1)

Output: "lul"
Explanation: There are two shortest ways for the ball to drop into the hole.
The first way is left -> up -> left, represented by "lul".
The second way is up -> left, represented by 'ul'.
Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. So the output is "lul".

Example 2

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (3, 0)
Output: "impossible"
Explanation: The ball cannot reach the hole.

Note:
There is only one ball and one hole in the maze.
Both the ball and hole exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.

*/

class Solution {
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    String[] dirStr = new String[] { "d", "u", "r", "l" };
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        if (maze == null || maze.length == 0) {
            return "impossible";
        }
        int m = maze.length;
        int n = maze[0].length;
        String[][] ways = new String[m][n];
        int[][] distance = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(ways[i], "z");
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        ways[ball[0]][ball[1]] = "";
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (distance[a[0]][a[1]] == distance[b[0]][b[1]]) {
                return ways[a[0]][a[1]].compareTo(ways[b[0]][b[1]]);
            } else {
                return distance[a[0]][a[1]] - distance[b[0]][b[1]];
            }
        });
        
        pq.add(ball);    
        
        while (!pq.isEmpty()) {
            int[] f = pq.poll();
            if (f[0] == hole[0] && f[1] == hole[1]) {
                return ways[f[0]][f[1]];
            }
            for (int i = 0; i < dirs.length; i++) {
                int x = f[0];
                int y = f[1];
                int length = distance[f[0]][f[1]];
                while (x >= 0 && y >= 0 && x < m && y < n && maze[x][y] == 0) {
                    x += dirs[i][0];
                    y += dirs[i][1];
                    length++;
                    if (hole[0] == x && hole[1] == y) {
                        if (distance[x][y] > length || distance[x][y] == length && (ways[f[0]][f[1]] + dirStr[i]).compareTo(ways[x][y]) < 0) {
                            distance[x][y] = length;
                            ways[x][y] = ways[f[0]][f[1]] + dirStr[i];
                            pq.add(new int[]{x, y});
                            continue;
                        }
                    }
                }
                
                x -= dirs[i][0];
                y -= dirs[i][1];
                length--;
               
                if (distance[x][y] > length || distance[x][y] == length && (ways[f[0]][f[1]] + dirStr[i]).compareTo(ways[x][y]) < 0) {
                    distance[x][y] = length;
                    ways[x][y] = ways[f[0]][f[1]] + dirStr[i];
                    pq.add(new int[]{x, y});
                }
            }
        }
        return "impossible";
    }
}
