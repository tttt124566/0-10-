/*
Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

Example 1:
Given points = [[1,1],[-1,1]], return true.

Example 2:
Given points = [[1,1],[-1,-1]], return false.

Follow up:
Could you do better than O(n2)?
*/
class Solution {
    public boolean isReflected(int[][] points) {
        if (points.length == 0) {
            return true;
        }
        int min = points[0][0];
        int max = points[0][0];
        Set<String> set = new HashSet<String>();
        for (int[] point: points) {
            min = Math.min(point[0], min);
            max = Math.max(point[0], max);
            String str = point[0] + "," + point[1];
            set.add(str);
        }
        int sum = min + max;
        for (int[] point: points) {
            String str = (sum - point[0]) + "," + point[1];
            if (!set.contains(str)) {
                return false;
            }
        }
        return true;
    }
}
