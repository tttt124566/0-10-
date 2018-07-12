/*
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        int n = (int)Math.pow(2, nums.length);
        
        for (int i = 0; i < n; i++) {
            List<Integer> tempList = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if (((i >> j) & 1) == 1) {
                    tempList.add(nums[j]);
                }
            }
            lists.add(tempList);
        }
        return lists;
    }
    
    // public List<List<Integer>> subsets(int[] nums) {
    //     List<List<Integer>> res = new ArrayList<List<Integer>>();
    //     List<Integer> tmp = new ArrayList<Integer>();
    //     res.add(tmp);
    //     subsetsHelper(nums, 0, res, tmp);
    //     return res;
    // }
    
//     private void subsetsHelper(int[] nums, int start, List<List<Integer>> res, List<Integer> tmp) {
//         if (start == nums.length) {
//             return;
//         }
//         for (int i = start; i < nums.length; i++) {
//             tmp.add(nums[i]);
//             res.add(new ArrayList<>(tmp)); // new arrayList
//             subsetsHelper(nums, i + 1, res, tmp);
//             tmp.remove(tmp.size() - 1);
//         }
//     }
}
