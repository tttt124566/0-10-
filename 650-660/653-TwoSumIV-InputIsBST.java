/*
Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True
Example 2:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False
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
    Stack<TreeNode> stack1;
    Stack<TreeNode> stack2;
    TreeNode node1;
    TreeNode node2;
    
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return k == 0;
        }
        stack1 = new Stack<>();
        stack2 = new Stack<>();
        node1 = root;
        node2 = root;
        TreeNode left = LMR();
        TreeNode right = RML();
        while (left.val < right.val) {
            int sum = left.val + right.val;
            if (sum == k) {
                return true;
            } else if (sum > k) {
                right = RML();
            } else {
                left = LMR();
            }
        }
        return false;
    }
    
    private TreeNode LMR(){
            while (node1 != null){
                stack1.push(node1);
                node1 = node1.left;
            }

            TreeNode temp = stack1.pop(); // pop the stack.
            node1 = temp.right;
            return temp;

    }


    private TreeNode RML(){
            while (node2 != null){
                stack2.push(node2);
                node2 = node2.right;
            }
            TreeNode temp = stack2.pop();
            node2 = temp.left;
            return temp;

    }
}
