/*
A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

Example:

Input: 

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 6 

Explanation: Given three people living at (0,0), (0,4), and (2,2):
             The point (0,2) is an ideal meeting point, as the total travel distance 
             of 2+2+2=6 is minimal. So return 6
*/

class Solution {
    List<Integer> rows;
    List<Integer> cols;
    public int minTotalDistance(int[][] grid) {
        rows = new ArrayList<Integer>();
        cols = new ArrayList<Integer>();
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        
        Collections.sort(rows);
        Collections.sort(cols);
        
        int medianRow = rows.size() % 2 == 0 ? (rows.get(rows.size() / 2) + rows.get((rows.size() + 1) / 2)) / 2 : rows.get(rows.size() / 2);
        int medianCol = cols.size() % 2 == 0 ? (cols.get(cols.size() / 2) + cols.get((cols.size() + 1) / 2)) / 2 :
        cols.get(cols.size() / 2);

        int distance = 0;
        for (int i = 0; i < rows.size(); i++) {
            distance += Math.abs(rows.get(i) - medianRow);
            distance += Math.abs(cols.get(i) - medianCol);
        }
        
        return distance;
    }
}
