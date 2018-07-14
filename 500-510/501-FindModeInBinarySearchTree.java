/*
Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
For example:
Given BST [1,null,2,2],
   1
    \
     2
    /
   2
return [2].

Note: If a tree has more than one mode, you can return them in any order.

Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
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
    
    int count = 1;
    int maxFrequency = 1;
    TreeNode prev = null;
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        
        List<Integer> modes = new ArrayList<>();
        findModeHelper(root, modes);
        int[] res = new int[modes.size()];
        for (int i = 0; i < modes.size(); i++) {
            res[i] = modes.get(i);
        }
        return res;
    }
    
    private void findModeHelper(TreeNode root, List<Integer> modes) {
        if (root == null) {
            return;
        }
        findModeHelper(root.left, modes);
        if (prev != null && prev.val == root.val) {
            count++;
        } else {
            count = 1;
        }
        
        if (count > maxFrequency) {
            maxFrequency = count;
            modes.clear();
            modes.add(root.val);
        } else if (count == maxFrequency) {
            modes.add(root.val);
        }
        
        prev = root;
        findModeHelper(root.right, modes);
    }
}
