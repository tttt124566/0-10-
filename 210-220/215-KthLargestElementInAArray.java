/*
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.
*/

class Solution {
    
    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int l = left + 1, r = right;
        while (l <= r) {
            if (nums[l] < pivot && nums[r] > pivot) {
                swap(nums, l, r);
            } else if (nums[l] >= pivot) {
                l++;
            } else if (nums[r] <= pivot) {
                r--;
            }
        }
        swap(nums, left, r);
        return r;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public int findKthLargest(int[] nums, int k) {
        int start = 0;
        int end = nums.length - 1;
        while (true) {
            int r = partition(nums, start, end);
            if (r == k - 1) {
                return nums[r];
            } else if (r > k - 1) {
                end = r - 1;
            } else {
                start = r + 1;
            }
        }
    }
}
