/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:

Input:
    2
   / \
  1   3
Output: true
Example 2:

    5
   / \
  1   4
     / \
    3   6
Output: false
Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
             is 5 but its right child's value is 4.
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
    private TreeNode previous;
    private boolean result; 
    
    // public boolean isValidBST(TreeNode root) {
    //     if (root == null) {
    //         return true;
    //     }
    //     boolean isLeftSubstreeValid = isValidBST(root.left);
    //     if (previous != null && previous.val >= root.val) {
    //         return false;
    //     }
    //     previous = root;
    //     boolean isRightSubstreeValid = isValidBST(root.right);
    //     return isLeftSubstreeValid && isRightSubstreeValid;
    // }
    public boolean isValidBST(TreeNode root) {
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            TreeNode currentNode = stack.pop();
            if (prev != null && prev.val >= currentNode.val) {
                return false;
            }
            prev = currentNode;
            node = currentNode.right;
        }
        
        return true;
    }
}
