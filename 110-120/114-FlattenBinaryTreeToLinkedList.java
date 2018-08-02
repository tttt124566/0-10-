/*

Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
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
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        TreeNode left = root.left;
        TreeNode right = root.right;
        TreeNode inter = left;
        while (inter != null && inter.right != null) {
            inter = inter.right;
        }
        if (inter != null) {
            root.left = null;
            root.right = left;
            inter.right = right;
        }
        flatten(root.right);
    }
}
