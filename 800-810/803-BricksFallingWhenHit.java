/*
We have a grid of 1s and 0s; the 1s in a cell represent bricks.  A brick will not drop if and only if it is directly connected to the top of the grid, or at least one of its (4-way) adjacent bricks will not drop.

We will do some erasures sequentially. Each time we want to do the erasure at the location (i, j), the brick (if it exists) on that location will disappear, and then some other bricks may drop because of that erasure.

Return an array representing the number of bricks that will drop after each erasure in sequence.

Example 1:
Input: 
grid = [[1,0,0,0],[1,1,1,0]]
hits = [[1,0]]
Output: [2]
Explanation: 
If we erase the brick at (1, 0), the brick at (1, 1) and (1, 2) will drop. So we should return 2.
Example 2:
Input: 
grid = [[1,0,0,0],[1,1,0,0]]
hits = [[1,1],[1,0]]
Output: [0,0]
Explanation: 
When we erase the brick at (1, 0), the brick at (1, 1) has already disappeared due to the last move. So each erasure will cause no bricks dropping.  Note that the erased brick (1, 0) will not be counted as a dropped brick.
 

Note:

The number of rows and columns in the grid will be in the range [1, 200].
The number of erasures will not exceed the area of the grid.
It is guaranteed that each erasure will be different from any other erasure, and located inside the grid.
An erasure may refer to a location with no brick - if it does, no bricks drop.

*/


class Solution {
    public int[] hitBricks(int[][] grid, int[][] hits) {
        removeHitBricks(grid, hits);
        markRemainingBricks(grid);
        return searchFallingBrick(grid, hits);
    }
    
    private void removeHitBricks(int[][] grid, int[][] hits) {
        for (int[] hit : hits) {
            grid[hit[0]][hit[1]] -= 1;
        }
    }
    
    private void markRemainingBricks(int[][] grid) {
        for (int j = 0; j < grid[0].length; j++) {
            if (grid[0][j] == 1) {
                deepSearch(grid, 0, j);
            }
        }
    }
    
    private int[] searchFallingBrick(int[][] grid, int[][] hits) {
        int[] result = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            if (grid[hits[i][0]][hits[i][1]] == 0) {
                grid[hits[i][0]][hits[i][1]] = 1;
                if (isConnectToTop(grid, hits[i][0], hits[i][1])) {
                    result[i] = deepSearch(grid, hits[i][0], hits[i][1]) - 1;
                } else {
                    result[i] = 0;
                }
            }
        }
        return result;
    }
    
    
    private boolean isConnectToTop(int[][] grid, int i, int j) {
        if(i == 0) return true;

        if (i - 1 >= 0 && grid[i - 1][j] == 2) {
            return true;
        }
        if (i + 1 < grid.length && grid[i + 1][j] == 2) {
            return true;
        }
        if (j - 1 >= 0 && grid[i][j - 1] == 2) {
            return true;
        }
        if (j + 1 < grid[0].length && grid[i][j + 1] == 2) {
            return true;
        }
        return false;
    }
    
    private int deepSearch(int[][] grid, int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
            return 0;
        }
        int effectiveBricks = 0;

        if (grid[row][col] == 1) {
            grid[row][col] = 2;
            effectiveBricks++;
            effectiveBricks += deepSearch(grid, row + 1, col);
            effectiveBricks += deepSearch(grid, row - 1, col);
            effectiveBricks += deepSearch(grid, row, col + 1);
            effectiveBricks += deepSearch(grid, row, col - 1);
        }
        
        return effectiveBricks;
    }
}
