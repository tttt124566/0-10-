/*
We define a harmonious array is an array where the difference between its maximum value and its minimum value is exactly 1.

Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.

Example 1:
Input: [1,3,2,2,5,2,3,7]
Output: 5
Explanation: The longest harmonious subsequence is [3,2,2,2,3].
Note: The length of the input array will not exceed 20,000.
*/

class Solution {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i], 0);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        
        int maxLength = 0;
        for (Integer key : map.keySet()) {
            if (map.containsKey(key - 1)) {
                maxLength = Math.max(map.get(key - 1) + map.get(key), maxLength);
            }
            
            if (map.containsKey(key + 1)) {
                maxLength = Math.max(map.get(key + 1) + map.get(key), maxLength);
            }
        }
        
        return maxLength;
    }
}
