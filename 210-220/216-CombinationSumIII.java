/*
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
*/

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        combinationSum3Helper(res, list, 1, k, n);
        return res;
    }
    
    private void combinationSum3Helper(List<List<Integer>> res, List<Integer> list, int start, int k, int n) {
        if (k == 0 && n == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        if (k < 0 || n < 0) {
            return;
        }
        
        for (int i = start; i < 10; i++) {
            list.add(i);
            combinationSum3Helper(res, list, i + 1, k - 1, n - i);
            list.remove(list.size() - 1);
        }
    }
}
