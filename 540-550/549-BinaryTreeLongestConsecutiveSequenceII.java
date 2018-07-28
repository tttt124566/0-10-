/*
Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:
Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
Example 2:
Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
Note: All the values of tree nodes are in the range of [-1e7, 1e7].


*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int res;
    public int longestConsecutive(TreeNode root) {
        res = 0;
        helper(root);
        return res;
    }
    public int[] helper(TreeNode root){
        int[] cur = new int[2];
        if(root == null) return cur;
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        
        if(root.left != null){
            if(root.left.val != root.val - 1) left[0] = 0;
            if(root.left.val != root.val + 1) left[1] = 0;
        }
        if(root.right != null){
            if(root.right.val != root.val - 1) right[0] = 0;
            if(root.right.val != root.val + 1) right[1] = 0;
        }
        
        cur[0] = Math.max(left[0], right[0]) + 1;
        cur[1] = Math.max(left[1], right[1]) + 1;
        
        res = Math.max(res, cur[0] + cur[1] - 1);
        return cur;
    }
    
    
    
}
