/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<>();
        pathSumHelper(root, path, list, 0, sum);
        return list;
    }
    
    private void pathSumHelper(TreeNode root, List<Integer> path, List<List<Integer>> list, int pathSum, int sum) {
        if (root == null) {
            return;
        }
        pathSum += root.val;
        path.add(root.val);
        if (root.left == null && root.right == null && pathSum == sum) {
            list.add(new ArrayList<>(path));
        }
        pathSumHelper(root.left, path, list, pathSum, sum);
        pathSumHelper(root.right, path, list, pathSum, sum);
        path.remove(path.size() - 1);
    }
}
