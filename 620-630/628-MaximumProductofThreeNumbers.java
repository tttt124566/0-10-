/*
Given an integer array, find three numbers whose product is maximum and output the maximum product.

Example 1:
Input: [1,2,3]
Output: 6
Example 2:
Input: [1,2,3,4]
Output: 24
Note:
The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.
*/

class Solution {
    public int maximumProduct(int[] nums) {
        int[] max = largestThreeNumbers(nums);
        int[] min = minTwoNumbers(nums);
        return Math.max(max[0] * max[1] * max[2], min[0] * min[1] * max[0]);
    }
    
    private int[] largestThreeNumbers(int[] nums) {
        int num1 = Integer.MIN_VALUE;
        int num2 = Integer.MIN_VALUE;
        int num3 = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > num1) {
                num3 = num2;
                num2 = num1;
                num1 = num;
            } else if (num > num2) {
                num3 = num2;
                num2 = num;
            } else if (num > num3) {
                num3 = num;
            }
        }
        
        return new int[]{num1, num2, num3};
    }
    
    private int[] minTwoNumbers(int[] nums) {
        int num1 = Integer.MAX_VALUE;
        int num2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num < num1) {
                num2 = num1;
                num1 = num;
            } else if (num < num2) {
                num2 = num;
            }
        }
        
        return new int[]{num1, num2};
    }
}
