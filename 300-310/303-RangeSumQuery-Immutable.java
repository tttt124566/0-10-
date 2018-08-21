/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.
*/

class NumArray {
    int[] sumArray;
    
    public NumArray(int[] nums) {
       if (nums == null || nums.length == 0) {
           return;
       }
       sumArray = new int[nums.length];

       sumArray[0] = nums[0];
       for (int i = 1; i < nums.length; i++) {
           sumArray[i] = sumArray[i - 1] + nums[i];
       } 
    }
    
    public int sumRange(int i, int j) {
        if (i == 0) {
            return sumArray[j];
        } else {
            return sumArray[j] - sumArray[i - 1];
        }
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
