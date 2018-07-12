/*
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
*/

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        permuteHelper(nums, lists, list);
        return lists;
    }
    
    private void permuteHelper(int[] nums, List<List<Integer>> lists, List<Integer> list) {
        if (list.size() == nums.length) {
            lists.add(new ArrayList<Integer>(list));
            return;
        }
        for (int j = 0; j < nums.length; j++) {
            if (list.contains(nums[j])) continue;
            list.add(nums[j]);
            permuteHelper(nums, lists, list);
            list.remove(list.size() - 1);
        }
    }
}

public class Solution {
    public List<List<Integer>> permute(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        permute(result, num, 0);
        return result;
    }
    
    private void permute(List<List<Integer>> result, int[] array, int start) {
		if (start >= array.length) {
			List<Integer> current = new ArrayList<Integer>();
			for (int a : array) {
			    current.add(a);
			}
			result.add(current);
		} else {
			for (int i = start; i < array.length; i++) {
				swap(array, start, i);
				permute(result, array, start+1);
				swap(array, start, i);
			}
		}
	}
	
	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
