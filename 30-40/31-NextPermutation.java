/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1


*/

class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        int index = -1;
        while (i > 0) {
            if (nums[i - 1] < nums[i]) {
                index = i - 1;
                break;
            }
            i--;
        }
        
        int j = nums.length - 1;
        if (index != -1) {
            while (j > index) {
                if (nums[j] > nums[index]) {
                    swap(nums, j, index);
                    break;
                }
                j--;
            }
        }
        
        i = index + 1;
        j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
}
