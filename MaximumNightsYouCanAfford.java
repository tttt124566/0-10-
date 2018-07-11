/*

Leetcode 相似问题: Leetcode 198/213/337 House Robber I/II/III
Provide a set of positive integers (an array of integers). Each integer represents number of nights user request on Airbnb.com. If you are a host, you need to design and implement an algorithm to find out the maximum number a night you can accommodate. The constrain is that you have to reserve at least one day between each request, so that you have time to clean the room.
Given a set of numbers in an array which represent number of consecutive days of AirBnB reservation requested, as a host, pick the sequence which maximizes the number of days of occupancy, at the same time, leaving at least 1 day gap in between bookings for cleaning. Problem reduces to finding max-sum of non-consecutive array elements.
// [5, 1, 1, 5] => 10
The above array would represent an example booking period as follows -
// Dec 1 – 5 // Dec 5 – 6 // Dec 6 – 7 // Dec 7 - 12
The answer would be to pick dec 1-5 (5 days) and then pick dec 7-12 for a total of 10 days of occupancy, at the same time, leaving at least 1 day gap for cleaning between reservations.
Similarly,
// [3, 6, 4] => 7
// [4, 10, 3, 1, 5] => 15
转移方程:
• f(0) = nums[0]
• f(1) = max(num[0], num[1])
• f(k) = max( f(k-2) + nums[k], f(k-1) )
*/

public class MaximumNightYouCanAfford {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int f1 = 0;
        int f2 = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int f = Math.max(f1 + nums[i], f2);
            f1 = f2;
            f2 =f;
        }
        return f2;
    }

    public static void main(String[] args) {
        MaximumNightYouCanAfford mnyca = new MaximumNightYouCanAfford();
        int robbed = mnyca.rob(new int[]{4, 10, 3, 1, 5});
        System.out.println(robbed);
        robbed = mnyca.rob(new int[]{3, 6, 4});
        System.out.println(robbed);
    }
}
