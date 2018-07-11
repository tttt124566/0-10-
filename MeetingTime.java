/*
Leetcode 相似问题: Leetcode 252/253 Meeting Rooms I/II http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=146537
给一组 meetings(每个 meeting 由 start 和 end 时间组成)。求出在所有输入 meeting 时间段内没有 会议，也就是空闲的时间段。每个 subarray 都已经 sort 好。
举例:
[[1, 3], [6, 7]], [[2, 4]], [[2, 3], [9, 12]]
返回
[[4, 6], [7, 9]]
这题最简单的方法就是把所有区间都拆成两个点，然后排序，然后扫􏰀，每次碰到一个点如果是 左端点就把 busy_employees 加 1，否则减 1，等到每次 busy_employees 为 0 时就是一个新的区 间。这样复杂度 O(MlogM)，M 是总共区间数。
follow up: 求不少于 k 个员工空闲的时间段(改一下 check count 的条件就可以了)
*/

package airbnb;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Interval {
    int start;
    int end;
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Point {
    int time;
    boolean isStart;

    Point(int time, boolean isStart) {
        this.time = time;
        this.isStart = isStart;
    }
}

public class MeetingTime {

    public List<Interval> getAvailableIntervals(List<List<Interval>> intervals, int k) {
        List<Interval> res = new ArrayList<>();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < intervals.size(); i++) {
            for (int j = 0; j < intervals.get(i).size(); j++) {
                Interval interval = intervals.get(i).get(j);
                Point startPoint = new Point(interval.start, true);
                Point endPoint = new Point(interval.end, false);
                points.add(startPoint);
                points.add(endPoint);
            }
        }

        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.time - o2.time;
            }
        });

        int count = 0;
        Integer availableStart = null;

        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);
            if (point.isStart) {
                count++;
                if (availableStart == null && i == 0 && count <= intervals.size() - k) {
                    availableStart = point.time;
                } else if (availableStart != null && count == intervals.size() - k + 1) {
                    res.add(new Interval(availableStart, point.time));
                    availableStart = null;
                }
            } else {
                count--;
                if (availableStart == null && count == intervals.size() - k && i < points.size() - 1) {
                    availableStart = point.time;
                } else if (availableStart != null && i == points.size() - 1 && count <= intervals.size() - k) {
                    res.add(new Interval(availableStart, point.time));
                    availableStart = null;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        MeetingTime mt = new MeetingTime();
        List<Interval> person1 = new ArrayList<>();
        Interval interval1 = new Interval(1, 3);
        Interval interval2 = new Interval(4, 6);
        Interval interval3 = new Interval(7, 8);
        Interval interval4 = new Interval(10, 11);
        person1.add(interval1);
        person1.add(interval2);
        person1.add(interval3);
        person1.add(interval4);
        List<Interval> person2 = new ArrayList<>();
        person2.add(new Interval(4, 8));
        List<List<Interval>> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);
        List<Interval> res = mt.getAvailableIntervals(list, 1);
        for (Interval interval : res) {
            System.out.println(interval.start);
            System.out.println(interval.end);
        }
    }
}
