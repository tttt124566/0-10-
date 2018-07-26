/*
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

    4
   / \
  2   5
 / \
1   3

Output: [4,3]
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?


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
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> list = new ArrayList<Integer>();
        closestKValuesHelper(root, target, k, list);
        return list;
    }
    
    private void closestKValuesHelper(TreeNode root, double target, int k, List<Integer> nodesValues) {
        if (root == null) {
            return;
        }
        closestKValuesHelper(root.left, target, k, nodesValues);
        if (nodesValues.size() == k) {
            if (Math.abs(nodesValues.get(0) - target) < Math.abs(root.val - target)) {
                return;
            } else {
                nodesValues.remove(0);
            }
        }
        nodesValues.add(root.val);
        closestKValuesHelper(root.right, target, k, nodesValues);
    }
}
