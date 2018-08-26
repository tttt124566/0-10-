/*
Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

*/
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        List<Integer>[] buckets = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (buckets[entry.getValue()] == null) {
                buckets[entry.getValue()] = new ArrayList<Integer>();
            }
            buckets[entry.getValue()].add(entry.getKey());
        }
        
        List<Integer> res = new ArrayList<>();
        for (int i = nums.length; i >= 0; i--) {
            if (k == 0) {
                break;
            }
            if (buckets[i] != null) {
                for (Integer s : buckets[i]) {
                    res.add(s);
                    k--;
                    if (k == 0) {
                        break;
                    }
                }
            }
        }
        
        return res;
    }
}
