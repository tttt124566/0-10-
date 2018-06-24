/*
Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is defined as the absolute difference between A and B.

Example 1:
Input:
nums = [1,3,1]
k = 1
Output: 0 
Explanation:
Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.
Note:
2 <= len(nums) <= 10000.
0 <= nums[i] < 1000000.
1 <= k <= len(nums) * (len(nums) - 1) / 2.
*/
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);

        int n = nums.length;
        int l = 0;
        int r = nums[n - 1] - nums[0];

        while(l < r) {
            int m = l + (r - l) / 2;

            int cnt = 0;
            for (int i = 0; i < n; i++) {
                int j = i;
                while (j < n && nums[j] <= nums[i] + m) j++;
                cnt += j - i - 1;
            }

            if (cnt < k) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        return l;
    }
