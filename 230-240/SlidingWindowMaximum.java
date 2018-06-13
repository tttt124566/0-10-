class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
			return new int[0];
		}
        int n = nums.length;
        int[] res = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque();
        int ri = 0;
        for (int i = 0; i < nums.length; i++) {
            // We need to pop out index which is out of the range.
            while (!dq.isEmpty() && dq.peekFirst() < i - k + 1) {
                dq.pollFirst();
            }
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }
            dq.offerLast(i); // push index.
            if (i >= k - 1) {
				res[ri++] = nums[dq.peek()];
			}
        }
        return res;
    }
}
