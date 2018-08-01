/*
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }
    
    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int prestart, int preend, int instart, int inend) {
        if (prestart > preend || instart > inend) {
            return null;
        } 
        int index = -1;
        for (int i = instart; i <= inend; i++) {
            if (inorder[i] == preorder[prestart]) {
                index = i;
                break;
            }
        }
        TreeNode root = new TreeNode(preorder[prestart]);
        root.left = buildTreeHelper(preorder, inorder, prestart + 1, prestart + index - instart, instart, index - 1);
        root.right = buildTreeHelper(preorder, inorder, prestart + index - instart + 1, preend, index + 1, inend);
        return root;
    }
}
