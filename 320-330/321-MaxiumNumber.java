/*

Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits.

Note: You should try to optimize your time and space complexity.

Example 1:

Input:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
Output:
[9, 8, 6, 5, 3]
Example 2:

Input:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
Output:
[6, 7, 6, 0, 4]
Example 3:

Input:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
Output:
[9, 8, 9]
*/

class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];
        for (int i = 0; i <= k; i++) {
            if (i <= nums1.length && (k - i) <= nums2.length) {
                int[] can1 = getMaxNumberArray(nums1, i);

                int[] can2 = getMaxNumberArray(nums2, k - i);

                // for (int t = 0; t < can1.length; t++) {
                //     System.out.print(can1[t]);
                // }
                // System.out.println('.');
                // for (int t = 0; t < can2.length; t++) {
                //     System.out.print(can2[t]);
                // }
                // System.out.println('.');   
                
                int[] can = merge(can1, can2, k);

                if (greater(can, 0, ans, 0)) {
                    ans = can;
                }
            }
        }
        
        return ans;
    }
    
    private int[] merge(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];
        for (int i = 0, j = 0, r = 0; r < k; ++r)
            ans[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        return ans;
    }
    
    public boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }
    
    private int[] getMaxNumberArray(int[] nums, int k) {
        int[] ans = new int[k];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            while (!stack.isEmpty() && cur > stack.peek() && (stack.size() - 1 + nums.length - i) >= k) {
                stack.pop();
            }
            if (stack.size() < k) {
                stack.push(nums[i]);
            }
        }
        
        while(!stack.isEmpty()) {
            ans[--k] = stack.pop();
        }
        
        return ans;
    }
}
