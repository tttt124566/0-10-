/*
A full binary tree is a binary tree where each node has exactly 0 or 2 children.

Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.

Each node of each tree in the answer must have node.val = 0.

You may return the final list of trees in any order.

 

Example 1:

Input: 7
Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
Explanation:

 

Note:

1 <= N <= 20

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
    public List<TreeNode> allPossibleFBT(int N) {
        return allPossibleFBTHelper(0, N - 1);
    }
    
    private List<TreeNode> allPossibleFBTHelper(int start, int end) {
        List<TreeNode> nodes = new ArrayList<>();

        if (start > end) {
            nodes.add(null);
            return nodes;
        }
        if (start == end) {
            nodes.add(new TreeNode(0));
            return nodes;
        }
        
        for (int i = start; i <= end; i++) {
            int left = i - start;
            int right = end - i;
            if (left % 2 == 1 && right % 2 == 1) {
                List<TreeNode> leftTrees = allPossibleFBTHelper(start, i - 1);
                List<TreeNode> rightTrees = allPossibleFBTHelper(i + 1, end);
                for (TreeNode leftTree : leftTrees) {
                    for (TreeNode rightTree: rightTrees) {
                        TreeNode root = new TreeNode(0);
                        root.left = leftTree;
                        root.right = rightTree;
                        nodes.add(root);
                    }
                }
            }
        }
        
        return nodes;
    }
}
