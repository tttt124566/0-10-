/*
Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        subsetsWithDupHelper(res, nums, 0, temp);
        return res;
    }
    
    private void subsetsWithDupHelper(List<List<Integer>> res, int[] nums, int start, List<Integer> temp) {
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            temp.add(nums[i]);
            res.add(new ArrayList<>(temp));
            subsetsWithDupHelper(res, nums, i + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }
}
