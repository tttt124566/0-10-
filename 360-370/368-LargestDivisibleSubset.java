/*
Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:

Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

Input: [1,2,3]
Output: [1,2] (of course, [1,3] will also be ok)
Example 2:

Input: [1,2,4,8]
Output: [1,2,4,8]
Seen this question in a real interview before?  
*/

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        Arrays.sort(nums);
        int[] count = new int[nums.length];
        int[] prev = new int[nums.length];
        Arrays.fill(count, 1);
        Arrays.fill(prev, -1);
        int maxCount = 1;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (count[j] + 1 > count[i]) {
                        count[i] = count[j] + 1;
                        prev[i] = j;
                    }
                }
            }
            
            if (count[i] > maxCount) {
                maxCount = count[i];
                index = i;
            }
        }
        
        largestDivisibleSubsetHelper(nums, prev, index, res);
        return res;
    }
    
    private void largestDivisibleSubsetHelper(int[] nums, int[] prev, int i, List<Integer> res) {
        res.add(0, nums[i]);
        if (prev[i] == -1) {
            return;
        }
        largestDivisibleSubsetHelper(nums, prev, prev[i], res);
    }
}
