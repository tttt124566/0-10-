class Solution {
    public int[] productExceptSelf(int[] nums) {
        // 2, 4, 6, 8, 5
        int[] res = new int[nums.length];
        int productFromLeft = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                res[i] = 1;
            } else {
                res[i] = productFromLeft * nums[i - 1];
            }
            productFromLeft = res[i];
        }
        
        int productFromRight = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            productFromRight = productFromRight * nums[i + 1];
            res[i] = res[i] * productFromRight;
        }
        return res;
    }
}
