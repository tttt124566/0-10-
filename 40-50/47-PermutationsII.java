/*

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

*/

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        int[] used = new int[nums.length];
        Arrays.sort(nums);
        permuteUniqueHelper(res, list, nums, used);
        return res; 
    }
    
    private void permuteUniqueHelper(List<List<Integer>> res, List<Integer> list, int[] nums, int[] used) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 1 || i != 0 && nums[i] == nums[i - 1] && used[i - 1] == 0) {
                continue;
            }
            list.add(nums[i]);
            used[i] = 1;
            permuteUniqueHelper(res, list, nums, used);
            list.remove(list.size() - 1);
            used[i] = 0;
        }
    }
}
