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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        levelOrderHelper(root, lists, 0);
        return lists;
    }
    
    private void levelOrderHelper(TreeNode root, List<List<Integer>> lists, int level) {
        if (root == null) {
            return;
        }
        if (lists.size() == level) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(root.val);
            lists.add(list);
        } else {
            lists.get(level).add(root.val);
        }
        levelOrderHelper(root.left, lists, level + 1);
        levelOrderHelper(root.right, lists, level + 1);
    }
}
