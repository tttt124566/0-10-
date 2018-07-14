/*
We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

Example 1:
Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation:
There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.
Example 2:
Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]
(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)

Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

Note:

schedule and schedule[i] are lists with lengths in range [1, 50].
0 <= schedule[i].start < schedule[i].end <= 10^8.

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
    class Point implements Comparable<Point> {
        int time;
        boolean isStart;
        
        Point(int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }
        
        @Override
        public int compareTo(Point p2){
           if (time != p2.time){
               return time - p2.time;
           }
           return (isStart ? -1 : 1);
        }
    }
    
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Point> list = new ArrayList<>();
        for (int i = 0; i < schedule.size(); i++) {
            for (int j = 0; j < schedule.get(i).size(); j++) {
                list.add(new Point(schedule.get(i).get(j).start, true));
                list.add(new Point(schedule.get(i).get(j).end, false));
            }
        }
        Collections.sort(list);
        int count = 0;
        List<Interval> res = new ArrayList<>();
        Integer availableStart = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isStart) {
                if (count == 0 && availableStart != null && availableStart != list.get(i).time) {
                    Interval freeInterval = new Interval(availableStart, list.get(i).time);
                    res.add(freeInterval);
                    availableStart = null;
                }
                count++;
            } else {
                count--;
                if (count == 0) {
                    availableStart = list.get(i).time;
                }
            }
        }
        return res;
    }
}
