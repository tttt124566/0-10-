/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF"
*/

class Solution {
    public int numDecodings(String s) {
        int[] nums = new int[s.length() + 1];
        nums[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            if (i >= 2 && Integer.parseInt(s.substring(i - 2, i)) >= 10 && Integer.parseInt(s.substring(i - 2, i)) <= 26) {
                nums[i] += nums[i - 2];
            }
            if (Integer.parseInt(s.substring(i - 1, i)) >= 1 && Integer.parseInt(s.substring(i - 1, i)) <= 9) {
                nums[i] += nums[i - 1];
            }
        }
        
        return nums[s.length()];
    }
}
