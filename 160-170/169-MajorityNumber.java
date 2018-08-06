/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2

*/

class Solution {
    public int majorityElement(int[] nums) {
        int frequency = 0;
        int majorityNumber = -1;
        for (int i = 0; i < nums.length; i++) {
            if (frequency == 0) {
                majorityNumber = nums[i];
                frequency++;
            } else {
                if (nums[i] == majorityNumber) {
                    frequency++;
                } else {
                    frequency--;
                }   
            }
        }
        
        return majorityNumber;
    }
}
