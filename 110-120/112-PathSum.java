/*
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.


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
    
    private boolean hasPathSum;
    public boolean hasPathSum(TreeNode root, int sum) {
        hasPathSum = false;
        hasPathSumHelper(root, 0, sum);
        return hasPathSum;
    }
    
    private void hasPathSumHelper(TreeNode root, int pathSum, int sum) {
        if (root == null) {
            return;
        }
        
        pathSum += root.val;
        if (root.left == null && root.right == null) {
            if (pathSum == sum) {
                hasPathSum = true;
            }
        }
        
        hasPathSumHelper(root.left, pathSum, sum);
        hasPathSumHelper(root.right, pathSum, sum);
    }
}
