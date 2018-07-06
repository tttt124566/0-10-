/*
Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
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
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<TreeNode>();
        }
        List<TreeNode> list = new ArrayList<TreeNode>();
        return generateTreesHelper(1, n, new List[n][n]);
    }
    
    private List<TreeNode> generateTreesHelper(int start, int end, List[][] dp) {
        if (start > end) {
            List<TreeNode> list = new ArrayList<>();
            list.add(null);
            return list;
        }
        
        if (dp[start - 1][end - 1] != null) {
            return dp[start - 1][end - 1];
        }
        List<TreeNode> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftPart = generateTreesHelper(start, i - 1, dp);
            List<TreeNode> rightPart = generateTreesHelper(i + 1, end, dp);
            for (int j = 0; j < leftPart.size(); j++) {
                for (int k = 0; k < rightPart.size(); k++) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftPart.get(j);
                    root.right = rightPart.get(k);
                    list.add(root);
                }
            }
        }
        
        dp[start - 1][end - 1] = list;
        return list;
    }
}
