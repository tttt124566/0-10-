/*
Given a binary tree

struct TreeLinkNode {
  TreeLinkNode *left;
  TreeLinkNode *right;
  TreeLinkNode *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
Recursive approach is fine, implicit stack space does not count as extra space for this problem.
Example:

Given the following binary tree,

     1
   /  \
  2    3
 / \    \
4   5    7
After calling your function, the tree should look like:

     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \    \
4-> 5 -> 7 -> NULL
*/

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        while (root != null) {
            TreeLinkNode p = root;
            while (p != null) {
                if (p.left != null) {
                    if (p.right != null) {
                        p.left.next = p.right;
                    } else {
                        p.left.next = getNextRightNode(p);
                    }
                }
                
                if (p.right != null) {
                    p.right.next = getNextRightNode(p);
                }
                p = p.next;
            }
            if (root.left != null) {
                root = root.left;
            } else if (root.right != null) {
                root = root.right;
            } else {
                root = root.next;
            }
        }
    }
    
    private TreeLinkNode getNextRightNode(TreeLinkNode root) {
        while (root != null && root.next != null) {
            if (root.next.left != null) {
                return root.next.left;
            } else if (root.next.right != null) {
                return root.next.right;
            } else {
                root = root.next;
            }
        }
        return null;
    }
}
