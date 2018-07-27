/*
On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).

Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.

You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?

Example 1:

Input: [[0,2],[1,3]]
Output: 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.

You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.
Example 2:

Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
Output: 16
Explanation:
 0  1  2  3  4
24 23 22 21  5
12 13 14 15 16
11 17 18 19 20
10  9  8  7  6

The final route is marked in bold.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
Note:

2 <= N <= 50.
grid[i][j] is a permutation of [0, ..., N*N - 1].
*/
class Solution {
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int swimInWater(int[][] grid) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> {
                return grid[a[0]][a[1]] - grid[b[0]][b[1]];
        });
        pq.add(new int[]{0, 0, grid[0][0]});
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        int res = 0;
        while (!pq.isEmpty()) {
            int[] first = pq.poll();
            res = Math.max(first[2], res);
            if (first[0] == grid.length - 1 && first[1] == grid[0].length - 1) {
                return res;
            }
            for (int[] dir: dirs) {
                int x = first[0] + dir[0];
                int y = first[1] + dir[1];
                if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || visited[x][y]) {
                    continue;
                }
                visited[x][y] = true;
                pq.add(new int[] {x, y, grid[x][y]});
            }
        }
        
        return Integer.MAX_VALUE;
    }
}
