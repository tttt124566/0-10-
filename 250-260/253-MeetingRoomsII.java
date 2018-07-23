/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    class Point {
        int time;
        boolean isStart;
        
        Point(int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }
        
        public int compareTo(Point o) {
            return this.time - o.time;
        }
    }
    
    public int minMeetingRooms(Interval[] intervals) {
        List<Point> timePoints = new ArrayList<Point>();
        for (int i = 0; i < intervals.length; i++) {
            timePoints.add(new Point(intervals[i].start, true));
            timePoints.add(new Point(intervals[i].end, false));
        }
        Collections.sort(timePoints, (a, b) -> {
            if (a.time == b.time) {
                return a.isStart == true ? 1 : -1;
            }
            return a.time - b.time;
        });
        
        int count = 0;
        int maxCount = 0;
        for (Point p : timePoints) {
            if (p.isStart) {
                count++;
            } else {
                count--;
            }
            maxCount = Math.max(maxCount, count);
        }
        
        return maxCount;
    }
}
