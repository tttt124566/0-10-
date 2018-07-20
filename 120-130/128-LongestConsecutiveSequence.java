/*

Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

*/
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        
        int longestLength = 1;
        while (!set.isEmpty()) {
            int length = 1;
            int val = set.iterator().next();
            set.remove(val);
            int rightNum = val + 1;
            int leftNum = val - 1;
            while (set.contains(rightNum)) {
                set.remove(rightNum++);
                length++;
            }
            
            while (set.contains(leftNum)) {
                set.remove(leftNum--);
                length++;
            }
            
            longestLength = Math.max(longestLength, length);
        }
        
        return longestLength;
    }
}
