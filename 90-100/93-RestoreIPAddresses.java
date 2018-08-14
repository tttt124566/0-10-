/*
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]
Seen this question in a real interview before?  
*/
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        restoreIpAddressesHelper(res, "", s.toCharArray(), 0, s.length(), 4);
        return res;
    }
    
    private void restoreIpAddressesHelper(List<String> result, String s, char[] a, int start, int end, int count) {
        if (end - start < count || end - start > 3 * count) {
            return;
        }

        if (start == end && count == 0) {
            result.add(s.substring(0, s.length() - 1));
            return;
        }
        
        
        int num = 0;
        for (int i = start; i < Math.min(start + 3, end); i++) {
            num = num * 10 + a[i] - '0';
            if (num >= 0 && num <= 255) {
                restoreIpAddressesHelper(result, s + num + ".", a, i + 1, end, count - 1);
            }
            if (num == 0) {
                break;
            }
        }
    }
}
