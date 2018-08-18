/*
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286

    4
   / \
  2   5
 / \
1   3

Output: 4
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
    private double difference = Double.MAX_VALUE;
    private int res;
    public int closestValue(TreeNode root, double target) {
        res = root.val;
        closestValueHelper(root, target);
        return res;
    }
    
    private void closestValueHelper(TreeNode root, double target) {
        if (root == null) {
            return;
        }
        if (Math.abs(root.val - target) < difference) {
            difference = Math.abs(root.val - target);
            res = root.val;
        }
        
        if (target < root.val) {
            closestValueHelper(root.left, target);
        } else {
            closestValueHelper(root.right, target);
        }
    }
}
