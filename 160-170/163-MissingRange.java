/*
Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

Example:

Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
Output: ["2", "4->49", "51->74", "76->99"]
*/

class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> list = new ArrayList<String>();
        long[] newNums = new long[nums.length + 2];
        newNums[0] = (long)lower - 1;
        for (int i = 1; i <= nums.length; i++) {
            newNums[i] = nums[i - 1];
        }
        newNums[nums.length + 1] = (long)upper + 1;
        long low = 0, up = 0;
        for (int i = 1; i < newNums.length; i++) {
            low = newNums[i - 1] + 1;
            up = newNums[i] - 1;
            if (low == up) {
                list.add(String.valueOf(low));
            } else if (low < up) {
                list.add(String.valueOf(low) + "->" + String.valueOf(up));
            }
        }
        return list;
    }
}
