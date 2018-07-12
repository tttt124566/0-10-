/*
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
*/
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        combinationSumHelper(res, list, candidates, 0, target);
        return res;
    }
    
    private void combinationSumHelper(List<List<Integer>> res, List<Integer> list, int[] candidates, int start, int target) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        
        if (target < 0) {
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            list.add(candidates[i]);
            combinationSumHelper(res, list, candidates, i, target - candidates[i]);
            list.remove(list.size() - 1);
        }
    }
}
