/*

Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTreeHelper(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }
    
    private TreeNode buildTreeHelper(int[] inorder, int[] postorder, int instart, int inend, int poststart, int postend) {
        if (instart > inend || poststart > postend) {
            return null;
        }
        
        int index = -1;
        for (int i = instart; i <= inend; i++) {
            if (inorder[i] == postorder[postend]) {
                index = i;
                break;
            }
        }
        
        TreeNode root = new TreeNode(postorder[postend]);
        root.left = buildTreeHelper(inorder, postorder, instart, index - 1, poststart, poststart + index - instart - 1);
        root.right = buildTreeHelper(inorder, postorder, index + 1, inend, poststart + index - instart, postend - 1);
        
        return root;
    }
}
