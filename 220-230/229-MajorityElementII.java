/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Note: The algorithm should run in linear time and in O(1) space.

Example 1:

Input: [3,2,3]
Output: [3]
Example 2:

Input: [1,1,1,3,3,2,2,2]
Output: [1,2]
*/

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int majority1 = 0;
        int majority2 = 1;
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == majority1) {
                count1++; 
            } else if (nums[i] == majority2) {
                count2++;
            } else if (count1 == 0) {
                majority1 = nums[i];
                count1++;
            } else if (count2 == 0) {
                majority2 = nums[i];
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        
        count1 = 0;
        count2 = 0;
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == majority1) {
                count1++;
            } else if (nums[i] == majority2) {
                count2++;
            }
        }
        
        if (count1 > nums.length / 3) {
            res.add(majority1);
        }
        if (count2 > nums.length / 3) {
            res.add(majority2);
        }
        
        return res;
    }
}
