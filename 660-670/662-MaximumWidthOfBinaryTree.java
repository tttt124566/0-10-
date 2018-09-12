/*
Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:
Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:
Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:
Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:
Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).


Note: Answer will in the range of 32-bit signed integer.


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
    public int widthOfBinaryTree(TreeNode root) {
        List<int[]> nodes = new ArrayList<>();
        widthOfBinaryTreeHelper(root, nodes, 0, 0);
        
        int res = 0;
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i)[0] != Integer.MAX_VALUE && nodes.get(i)[1] != Integer.MIN_VALUE) {
                res = Math.max(res, nodes.get(i)[1] - nodes.get(i)[0] + 1);
            }
        }
        return res;
    }
    
    private void widthOfBinaryTreeHelper(TreeNode root, List<int[]> nodes, int level, int idx) {
        if (root == null) {
            return;
        }
        if (nodes.size() == level) {
            nodes.add(new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE});
        }
        System.out.println(level + ", " + idx);
        nodes.get(level)[0] = Math.min(nodes.get(level)[0], idx);
        nodes.get(level)[1] = Math.max(nodes.get(level)[1], idx);
        widthOfBinaryTreeHelper(root.left, nodes, level + 1, 2 * idx + 1);
        widthOfBinaryTreeHelper(root.right, nodes, level + 1, 2 * idx + 2);
    }
}
