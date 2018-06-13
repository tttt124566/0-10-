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
    public boolean isValidBST(TreeNode root) {
        // if (root == null) {
        //     return true;
        // }
        // Stack<TreeNode> stack = new Stack<TreeNode>();
        // TreeNode prev = null;
        // while (root != null || !stack.isEmpty()) {
        //     while (root != null) {
        //         stack.push(root);
        //         root = root.left;
        //     }
        //     root = stack.pop();
        //     if (prev != null && prev.val >= root.val) {
        //         return false;
        //     }
        //     prev = root;
        //     root = root.right;
        // }
        // return true;
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE); // change to long to cover integer.maxvalue and integer.minvalue
    }
    
    private boolean isValidBSTHelper(TreeNode root, long minValue, long maxValue) {
        if (root == null) {
            return true;
        }
        if (root.val >= maxValue || root.val <= minValue) {
            return false;
        }
        return isValidBSTHelper(root.left, minValue, root.val) && isValidBSTHelper(root.right, root.val, maxValue);
    }
}
