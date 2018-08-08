/*
Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:

Input: [10,2]
Output: "210"
Example 2:

Input: [3,30,34,5,9]
Output: "9534330"
Note: The result may be very large, so you need to return a string instead of an integer.
*/

class Solution {
    public String largestNumber(int[] nums) {
        String[] numStrings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numStrings[i] = String.valueOf(nums[i]);
        }
        
        PriorityQueue<String> pq = new PriorityQueue<>((str1, str2) -> {
            String s1 = str1 + str2;
			String s2 = str2 + str1;
			return s2.compareTo(s1); // reverse order here, so we can do append() later
        });
            
        for (int i = 0; i < numStrings.length; i++) {
            pq.add(numStrings[i]);
        } 
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll());
        }
        String res = sb.toString();
        if (res.charAt(0) == '0') {
            return "0";
        }
        
        return res;
    }
}
