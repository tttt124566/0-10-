/*Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.

Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is represented as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).


Example 1:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [3,2,4,4],
  [1,3,2,4],
  [2,3,3,4]
]

Return true. All 5 rectangles together form an exact cover of a rectangular region.

Example 2:

rectangles = [
  [1,1,2,3],
  [1,3,2,4],
  [3,1,4,2],
  [3,2,4,4]
]

Return false. Because there is a gap between the two rectangular regions.

Example 3:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [3,2,4,4]
]

Return false. Because there is a gap in the top center.

Example 4:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [2,2,4,4]
]

Return false. Because two of the rectangles overlap with each other.
*/
class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        if (rectangles == null || rectangles.length == 0) {
            return true;
        }
        
        int x1 = Integer.MAX_VALUE;
        int y1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;
        int y2 = Integer.MIN_VALUE;
        int area = 0;
        
        Set<String> pointSet = new HashSet<>();
        for (int[] rectangle: rectangles) {
            x1 = Math.min(rectangle[0], x1);
            y1 = Math.min(rectangle[1], y1);
            x2 = Math.max(rectangle[2], x2);
            y2 = Math.max(rectangle[3], y2);
            
            area += (rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]);

            String s1 = rectangle[0] + " " + rectangle[1];
            String s2 = rectangle[0] + " " + rectangle[3];
            String s3 = rectangle[2] + " " + rectangle[3];
            String s4 = rectangle[2] + " " + rectangle[1];
            
            if(!pointSet.add(s1)) pointSet.remove(s1);
            if(!pointSet.add(s2)) pointSet.remove(s2);
            if(!pointSet.add(s3)) pointSet.remove(s3);
            if(!pointSet.add(s4)) pointSet.remove(s4);
        }
        
        if (!pointSet.contains(x1 + " " + y1) || !pointSet.contains(x1 + " " + y2) || !pointSet.contains(x2 + " " + y1) || !pointSet.contains(x2 + " " + y2) || pointSet.size() != 4) return false;
        
        return area == (x2 - x1) * (y2 - y1);
    }
}
