/*
A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5 
Explanation: Your function can return either index number 1 where the peak element is 2, 
             or index number 5 where the peak element is 6.
Note:

Your solution should be in logarithmic complexity.

*/
class Solution {
    public int findPeakElement(int[] nums) {
        return findPeakElementHelper(nums, 0, nums.length - 1);
    }
    
    private int findPeakElementHelper(int[] nums, int i, int j) {
        if (i == j) {
            return i;
        }
        if (i + 1 == j) {
            if (nums[i] > nums[j]) {
                return i;
            } else {
                return j;
            }
        }
        int m = i + (j - i) / 2;
        if (nums[m] > nums[m + 1] && nums[m] > nums[m - 1]) {
            return m;
        } else if (nums[m] < nums[m + 1] && nums[m - 1] < nums[m]) {
            return findPeakElementHelper(nums, m + 1, j);
        } else {
            return findPeakElementHelper(nums, i, m - 1);
        }
    }
}
