/*
Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:

The solution set must not contain duplicate quadruplets.

Example:

Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
*/

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j != 0 && nums[j] == nums[j - 1]) {
                    continue;
                }
                
                int k = j + 1;
                int t = nums.length - 1;
                while (k < t) {
                    int sum = nums[i] + nums[j] + nums[k] + nums[t];
                    if (sum == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        list.add(nums[t]);
                        res.add(list);
                        k++;
                        t--;
                        while (k < nums.length && nums[k] == nums[k - 1]) {
                            k++;
                        }
                        
                        while (t >= 0 && nums[t] == nums[t + 1]) {
                            t--;
                        }
                    } else if (sum > target) {
                        t--;
                    } else {
                        k++;
                    }
                }
            }
        }
        
        return res;
    }
}
