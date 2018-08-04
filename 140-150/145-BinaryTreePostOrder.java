/*
Given a binary tree, return the postorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [3,2,1]
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
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> nodes = new Stack<>();
        Stack<Integer> preorder = new Stack<>();
        List<Integer> postOrder = new ArrayList<>();
        while (!nodes.isEmpty() || root != null) {
            if (root != null) {
                nodes.push(root);
                preorder.push(root.val);
                root = root.right;
            } else {
                root = nodes.pop().left;
            }
        }
        while(!preorder.isEmpty()) {
            postOrder.add(preorder.pop());
        }
        
        return postOrder;
        
    }
}
