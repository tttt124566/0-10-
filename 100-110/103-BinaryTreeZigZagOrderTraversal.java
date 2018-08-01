/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Boolean fromLeft = true;
        // next level nodes;
        List<TreeNode> currentLevel = new ArrayList<>();
        currentLevel.add(root);
        
        while (!currentLevel.isEmpty()) {
            // get values of nodes and add to result.
            List<Integer> levelValues = new ArrayList<>();
            for (int i = 0; i < currentLevel.size(); i++) {
                levelValues.add(currentLevel.get(i).val);
            }
            if (fromLeft) {
                res.add(levelValues);
            } else {
                Collections.reverse(levelValues);
                res.add(levelValues);
            }
            
            fromLeft = !fromLeft;
            
            List<TreeNode> nextLevel = new ArrayList<>();
            
            for (TreeNode node: currentLevel) {
                if (node.left != null) {
                    nextLevel.add(node.left);
                }
                if (node.right != null) {
                    nextLevel.add(node.right);
                }
            }
            
            currentLevel = nextLevel;
        }
        return res;
    }
}
