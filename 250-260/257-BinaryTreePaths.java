/*
Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3
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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<String>();
        binaryTreePathsHelper(root, "", res);
        
        return res;
    }
    
    private void binaryTreePathsHelper(TreeNode root, String path, List<String> paths) {
        if (root == null) {
            return;
        }
        
        if (root.left == null && root.right == null) {
            path += root.val;
            paths.add(path);
            return;
        }
        binaryTreePathsHelper(root.left, path + root.val + "->", paths);
        binaryTreePathsHelper(root.right, path + root.val + "->", paths);
    }
}
