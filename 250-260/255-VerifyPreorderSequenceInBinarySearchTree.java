/*
Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Consider the following binary search tree: 

     5
    / \
   2   6
  / \
 1   3
Example 1:

Input: [5,2,6,1,3]
Output: false
Example 2:

Input: [5,2,1,3,6]
Output: true
Follow up:
Could you do it using only constant space complexity?
*/

class Solution {
    public boolean verifyPreorder(int[] preorder) {
        return verifyPreorderHelper(preorder, 0, preorder.length - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private boolean verifyPreorderHelper(int[] preorder, int start, int end, int low, int high) {
        if (start > end) {
            return true;
        }
        
        int top = preorder[start];
        if (top > high || top < low) {
            return false;
        }
        
        int index = -1;
        for (int i = start + 1; i <= end; i++) {
            if (preorder[i] > top) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return verifyPreorderHelper(preorder, start + 1, end, low, top);
        }
        
        return verifyPreorderHelper(preorder, start + 1, index - 1, low, top) &&
            verifyPreorderHelper(preorder, index, end, top, high);
    }
}
