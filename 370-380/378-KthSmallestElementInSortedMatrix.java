/*

Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.

*/

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int low = matrix[0][0];
        int high = matrix[matrix.length - 1][matrix[0].length - 1];
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (getCount(matrix, mid) < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
    
    private int getCount(int[][] matrix, int mid) {
        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            int j = 0;
            // while (j < matrix[0].length && matrix[i][j] <= mid) {
            //     j++;
            // }
            // res += j;
            res += upperBound2(matrix[i], mid);
        }
        return res;
    }
    
    // Returns index of first index of element which is greater than key
    private int upperBound(int[] a, int low, int high, int key) {
        if (a[high] <= key) return high + 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (key >= a[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
    
    private int upperBound2(int[] nums, int key) {
        int low = 0;
        int high = nums.length - 1;
        if (nums[high] <= key) {
            return high + 1;
        }
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] <= key) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
    
}
