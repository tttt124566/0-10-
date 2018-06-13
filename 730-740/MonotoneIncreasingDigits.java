class Solution {
    // 3 3 2 
    public int monotoneIncreasingDigits(int N) {
        if (N <= 9) {
            return N;
        }
        char[] nums = String.valueOf(N).toCharArray();
        int mark = nums.length;
        for (int i = nums.length - 1; i >= 1; i--) {
            if (nums[i] < nums[i - 1]) {
                mark = i - 1;
                nums[i - 1]--;
            }
        }
        for (int i = mark + 1; i < nums.length; i++) {
            nums[i] = '9';
        }
        return Integer.valueOf(new String(nums));
    }
}
