/*

Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?


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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> nodes = new Stack<>();
        while (root != null || !nodes.isEmpty()) {
            if (root != null) {
                nodes.push(root);
                root = root.left;
            } else {
                TreeNode temp = nodes.pop();
                list.add(temp.val);
                root = temp.right;
            }
        }
        return list;
    }
}
