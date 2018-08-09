/*
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---

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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        rightSideViewHelper(root, res, 0);
        return res;
    }
    
    private void rightSideViewHelper(TreeNode root, List<Integer> res, int level) {
        if (root == null) {
            return;
        }
        
        if (level == res.size()) {
            res.add(root.val);
        }
        rightSideViewHelper(root.right, res, level + 1);
        rightSideViewHelper(root.left, res, level + 1);
    }
}
