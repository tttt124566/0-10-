/*
Given a list of points that form a polygon when joined sequentially, find if this polygon is convex (Convex polygon definition).

Note:

There are at least 3 and at most 10,000 points.
Coordinates are in the range -10,000 to 10,000.
You may assume the polygon formed by given points is always a simple polygon (Simple polygon definition). In other words, we ensure that exactly two edges intersect at each vertex, and that edges otherwise don't intersect each other.
Example 1:

[[0,0],[0,1],[1,1],[1,0]]

Answer: True

Explanation:
Example 2:

[[0,0],[0,10],[10,10],[10,0],[5,5]]

Answer: False

Explanation:
*/

class Solution {
    public boolean isConvex(List<List<Integer>> points) {
        boolean allPositive = false;
        boolean allNegative = false;
        
        for (int i = 0; i < points.size(); i++) {
            int j = (i + 1) % points.size();
            int k = (j + 1) % points.size();
            int crossProduct = crossProductLength(
                points.get(i).get(0), 
                points.get(i).get(1),
                points.get(j).get(0), 
                points.get(j).get(1),
                points.get(k).get(0), 
                points.get(k).get(1)
            );
            if (crossProduct < 0) {
                allPositive = true;
            }
            if (crossProduct > 0) {
                allNegative = true;
            }
            if (allPositive && allNegative) {
                return false;
            }
        }
        
        return true;
    }
    
    private int crossProductLength(int Ax, int Ay, int Bx, int By, int Cx, int Cy) {
        return (Cy - By) * (Bx - Ax) - (Cx - Bx) * (By - Ay);
    }
}
