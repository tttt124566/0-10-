/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3
*/
class Solution {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    clearIslands(grid, i, j, grid.length, grid[i].length);
                }
            }
        }
        return count;
    }
    
    // m: row size
    // n: col size
    private void clearIslands(char[][] grid, int i, int j, int m, int n) {
        if (i >= m || j >= n || i < 0 || j < 0 || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        for (int[] dir : dirs) {
            clearIslands(grid, i + dir[0], j + dir[1], m, n);
        }
    }
}

class UF {
    public int count = 0;
    public int[] id = null;

    public UF(int m, int n, char[][] grid) {
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '1') count++;
            }
        }
        id = new int[m * n];
        for(int i = 0; i < m * n; i++) {
            id[i] = i;
        }
    }

    public int find(int p) {
        while(p != id[p]) {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }

    public boolean isConnected(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot != qRoot) return false;
        else return true;
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot == qRoot) return;
        id[pRoot] = qRoot;
        count--;
    }
}

public int numIslands(char[][] grid) {
    if(grid.length == 0 || grid[0].length == 0) return 0;
    int m = grid.length, n = grid[0].length;
    UF uf = new UF(m , n, grid);
    
    for(int i = 0; i < m; i++) {
        for(int j = 0; j < n; j++) {
            if(grid[i][j] == '0') continue;
            int p = i * n + j;
            int q;
            if(i > 0 && grid[i - 1][j] == '1') {
                q = p - n;
                uf.union(p, q);
            }
            if(i < m - 1 && grid[i + 1][j] == '1') {
                q = p + n;
                uf.union(p, q);
            }
            if(j > 0 && grid[i][j - 1] == '1') {
                q = p - 1;
                uf.union(p, q);
            }
            if(j < n - 1 && grid[i][j + 1] == '1') {
                q = p + 1;
                uf.union(p, q);
            }
        }
    }
    return uf.count;
}
