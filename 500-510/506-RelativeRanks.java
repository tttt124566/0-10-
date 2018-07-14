/*
Given scores of N athletes, find their relative ranks and the people with the top three highest scores, who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".

Example 1:
Input: [5, 4, 3, 2, 1]
Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
Explanation: The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and "Bronze Medal". 
For the left two athletes, you just need to output their relative ranks according to their scores.
Note:
N is a positive integer and won't exceed 10,000.
All the scores of athletes are guaranteed to be unique.
*/

class Solution {
    class Point {
        int num;
        int index;
        
        Point(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }
    
    public String[] findRelativeRanks(int[] nums) {
        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> b.num - a.num);
        for (int i = 0; i < nums.length; i++) {
            pq.offer(new Point(nums[i], i));
        }
        
        String[] res = new String[nums.length];
        int three = 3;
        int count = 0;
        while (pq.size() > 0) {
            Point first = pq.poll();
            if (three == 3) {
                res[first.index] = "Gold Medal";
                three--;
            } else if (three == 2) {
                res[first.index] = "Silver Medal";
                three--;
            } else if (three == 1) {
                res[first.index] = "Bronze Medal";
                three--;
            } else {
                res[first.index] = String.valueOf(count + 1);
            }
            count++;
        }
        
        return res;
    }
}
