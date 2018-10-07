/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
Note:

You can assume that you can always reach the last index.
*/

class Solution {
    public int jump(int[] nums) {
       int n = nums.length;
       int count = 0;
       int next = 0;
       int i = 0;
       while (i < n-1)
       {
           next = i;
           int nextrange = i + nums[i];
           for (int j = i+1; j <= i + nums[i]; j++)
           {
               if (j >= n-1)
               {
                    next = n-1;
                    break;
               }
               if (j + nums[j] >= nextrange)
                {
                    nextrange = j + nums[j];
                    next = j;
                }
           }
           count ++;
           i = next;
       }
       return count;
    }
}
