/*
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

*/

class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int imax = nums[0];
        int imin = nums[0];
        int maxProduct = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int maxValue = Math.max(Math.max(imax * nums[i], imin * nums[i]), nums[i]);
            int minValue = Math.min(Math.min(imax * nums[i], imin * nums[i]), nums[i]);
            imax = maxValue;
            imin = minValue;
            maxProduct = Math.max(Math.max(maxValue, minValue), maxProduct);
        }
        return maxProduct;
    }
}
