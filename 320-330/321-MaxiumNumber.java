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
        for (int i = 0; i < k; i++) {
            int[] can1 = getMaxNumberArray(nums1, i);

            int[] can2 = getMaxNumberArray(nums2, k - i);

            int[] can = merge(can1, can2, k);
        
            if (greater(can, ans, k)) {
                ans = can;
            }
        }
        
        return ans;
    }
    
    private boolean greater(int[] nums1, int[] nums2, int k) {
        int i = 0;
        while (i < nums1.length && nums1[i] == nums2[i]) {
            i++;
        }
        if (i == k) {
            return true;
        }
        
        return nums1[i] > nums2[i];
    }
    
    private int[] merge(int[] nums1, int[] nums2, int k) {
        
        int i = 0;
        int j = 0;
        int[] ans = new int[k];
        int t = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] > nums2[j]) {
                ans[t++] = nums1[i++];
            } else {
                ans[t++] = nums2[j++];
            }
        }
        
        while (i < nums1.length) {
            ans[t++] = nums1[i++];
        }
        
        while (j < nums2.length) {
            ans[t++] = nums2[j++];

        }
        
        return ans;
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
