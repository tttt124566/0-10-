/*
You are given an integer array sorted in ascending order (may contain duplicates), you need to split them into several subsequences, where each subsequences consist of at least 3 consecutive integers. Return whether you can make such a split.

Example 1:
Input: [1,2,3,3,4,5]
Output: True
Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3
3, 4, 5
Example 2:
Input: [1,2,3,3,4,4,5,5]
Output: True
Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3, 4, 5
3, 4, 5
Example 3:
Input: [1,2,3,4,4,5]
Output: False
Note:
The length of the input is in range of [1, 10000]

*/

class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> unusedMap = new HashMap<Integer, Integer>();
        Map<Integer, Integer> needMap = new HashMap<Integer, Integer>();
        for (int i : nums) {
            unusedMap.put(i, unusedMap.getOrDefault(i, 0) + 1);
        }
        for (int i : nums) { 
            if (unusedMap.get(i) == 0) {
                continue;
            }
            if (needMap.getOrDefault(i, 0) > 0) {
                needMap.put(i, needMap.get(i) - 1);
                unusedMap.put(i, unusedMap.get(i) - 1);
                needMap.put(i + 1, needMap.getOrDefault(i + 1, 0) + 1);
            } else if (a
                unusedMap.getOrDefault(i + 1, 0) > 0 &&
                unusedMap.getOrDefault(i + 2, 0) > 0
            ) {
                unusedMap.put(i, unusedMap.get(i) - 1);
                unusedMap.put(i + 1, unusedMap.get(i + 1) - 1);
                unusedMap.put(i + 2, unusedMap.get(i + 2) - 1);
                needMap.put(i + 3, needMap.getOrDefault(i + 3, 0) + 1);
            } else {
                return false;
            }
        }
        return true;
    }
}
