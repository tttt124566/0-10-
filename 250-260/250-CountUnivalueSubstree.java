/*
Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

Example :

Input:  root = [5,1,5,5,5,null,5]

              5
             / \
            1   5
           / \   \
          5   5   5

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
    private int count;
    public int countUnivalSubtrees(TreeNode root) {
        
        count = 0;
        countUnivalSubtreesHelper(root);
        return count;
    }
    
    private boolean countUnivalSubtreesHelper(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean leftCheck = countUnivalSubtreesHelper(root.left);
        boolean rightCheck = countUnivalSubtreesHelper(root.right);
        
        if (leftCheck && 
            rightCheck &&
            (root.left == null || root.left.val == root.val) &&
            (root.right == null || root.right.val == root.val)
         ) {
            count++;
            return true;
        }
        
        return false;
    }
}
