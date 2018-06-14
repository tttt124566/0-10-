/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }
    
    private void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val);
        sb.append(",");
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] nums = data.split(",");
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = Integer.valueOf(nums[i]);
        }
        return deserializeHelper(res, 0, res.length - 1);
    }
    
    private TreeNode deserializeHelper(int[] data, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(data[start]);
        }
        TreeNode root = new TreeNode(data[start]);
        int i = start + 1;
        while (i <= end) {
            if (data[i] > data[start]) {
                break;
            }
            i++;
        }
        root.left = deserializeHelper(data, start + 1, i - 1);
        root.right = deserializeHelper(data, i, end);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
