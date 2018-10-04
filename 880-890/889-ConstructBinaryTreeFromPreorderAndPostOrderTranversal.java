/*
Return any binary tree that matches the given preorder and postorder traversals.

Values in the traversals pre and post are distinct positive integers.

 

Example 1:

Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]
 

Note:

1 <= pre.length == post.length <= 30
pre[] and post[] are both permutations of 1, 2, ..., pre.length.
It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
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
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if(pre.length == 0 || post.length == 0 || pre.length != post.length)
            return null;
        return dfs(pre, 0, pre.length - 1, post, 0, post.length - 1);
    }
    private TreeNode dfs(int[] pre, int ps, int pe, int[] post, int pps, int ppe) {
        if(ps > pe || pps > ppe) return null;
        TreeNode root = new TreeNode(pre[ps]);
        if(ps + 1 > pe) return root;
				// this is the start of the left tree
        int val = pre[ps + 1], idx = pps;
        for(; idx < ppe; idx++) {
            if(post[idx] == val) break;
        }
        root.left = dfs(pre, ps + 1, ps + idx - pps + 1, post, pps, idx);
        root.right = dfs(pre, ps + idx - pps + 2, pe, post, idx + 1, ppe - 1);
        return root;
    }
}
