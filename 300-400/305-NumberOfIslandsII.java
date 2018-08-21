/*
A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
Explanation:

Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
Follow up:

Can you do it in time complexity O(k log mn), where k is the length of the positions?


*/

class Solution {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int cnt = m * n;
        int[] nums = new int[m * n];
        Arrays.fill(nums, -1);
        int count = 0;
        List<Integer> list = new ArrayList<Integer>();
        for (int[] p : positions) {
            int ipx = p[0] * n + p[1];
            nums[ipx] = ipx;
            count++;
            for (int[] dir : dirs) {
                int x = p[0] + dir[0];
                int y = p[1] + dir[1];
                if (x < 0 || y < 0 || x >= m || y >= n || nums[x * n + y] == -1) {
                    continue;
                }
                int qRoot = find(x * n + y, nums);
                int pRoot = find(p[0] * n + p[1], nums);
                if (qRoot != pRoot) {
                    nums[qRoot] = pRoot;
                    count--;                        
                }
            }
            
            list.add(count);
        }
        
        return list;
    }
    
    private int find(int i, int[] nums) {
        while (nums[i] != i) {
            nums[i] = nums[nums[i]];
            i = nums[i];
        }
        return i;
    }
}
