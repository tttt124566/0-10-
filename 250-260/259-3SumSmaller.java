/*
Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

Example:

Input: nums = [-2,0,1,3], and target = 2
Output: 2 
Explanation: Because there are two triplets which sums are less than 2:
             [-2,0,1]
             [-2,0,3]

*/

class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int res = target - nums[i];
            int k = i + 1;
            int t = nums.length - 1;
            while (k < t) {
                if (nums[k] + nums[t] < res) {
                    count += t - k;
                    k++;
                } else {
                    t--;
                }
            }
        }
        
        return count;
    }
}
