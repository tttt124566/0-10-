/*
Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considerred overlapping.
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
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> list = new ArrayList<Interval>();
        if (intervals.size() == 0) {
            return list;
        }
        Collections.sort(intervals, (a, b) -> a.start - b.start);
        Interval currentInterval = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start <= currentInterval.end) {
                currentInterval.end = Math.max(currentInterval.end, intervals.get(i).end);
            } else {
                list.add(currentInterval);
                currentInterval = intervals.get(i);
            }
        }
        list.add(currentInterval);
        return list;
    }
}
