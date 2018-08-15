/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

Example:

Input: low = "50", high = "100"
Output: 3 
Explanation: 69, 88, and 96 are three strobogrammatic numbers.
Note:
Because the range might be a large number, the low and high numbers are represented as string.
*/
class Solution {
    Map<Character, Character> map = new HashMap<>();

     public int strobogrammaticInRange(String low, String high) {
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        map.put('6', '9');
        map.put('9', '6');

        if (low == null || high == null || low.length() > high.length()
            || (low.length() == high.length() && low.compareTo(high) > 0)) {
            return 0;
        }
        int count = 0;
        for (int len = low.length(); len <= high.length(); len++) {
            count += strobogrammaticInRangeHelper(0, len - 1, low, high, new char[len]);
        }
        return count;
    }
    
    private int strobogrammaticInRangeHelper(int left, int right, String low, String high, char[] s) {
        if (left > right) {
            String num = new String(s);
			if((num.length() == low.length() && num.compareTo(low) < 0 ) ||
               (num.length() == high.length() && num.compareTo(high) > 0)) {
                return 0;
            }
            
            return 1;
        }
        
        if (left == right) {
            char[] arrays = {'1', '8', '0'};
            int count = 0;
            for (int i = 0; i < arrays.length; i++) {
                s[left] = arrays[i];
                String num = new String(s);
                if((num.length() == low.length() && num.compareTo(low) < 0 ) ||
                   (num.length() == high.length() && num.compareTo(high) > 0)) {
                    continue;
                } 

                count++;
            }
            
            return count;
        }
        
        int count = 0;

        for (Character key : map.keySet()) {
            if (left == 0 && key == '0') {
                continue;
            }
            s[left] = key;
            s[right] = map.get(key);
            count += strobogrammaticInRangeHelper(left + 1, right - 1, low, high, s);
        }
        return count;
    }
}
