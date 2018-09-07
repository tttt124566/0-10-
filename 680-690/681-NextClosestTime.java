/*
Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.

You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.

Example 1:

Input: "19:34"
Output: "19:39"
Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.
Example 2:

Input: "23:59"
Output: "22:22"
Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.
*/

class Solution {
    public String nextClosestTime(String time) {
        int[] digits = new int[4];
        int m = 0;
        for (int i = 0; i < time.length(); i++) {
            if (time.charAt(i) != ':') {
                digits[m++] = time.charAt(i) - '0';
            }
        }
        
        String res = time;
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    for (int t = 0; t < 4; t++) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(digits[i] + "");
                        sb.append(digits[j] + ":");
                        sb.append(digits[k] + "");
                        sb.append(digits[t] + "");
                        if (!isValid(sb.toString()) || time.equals(sb.toString())) {
                            continue;
                        } else {
                            int compared = compareTime(time, sb.toString());
                            if (compared < diff) {
                                diff = compared;
                                res = sb.toString();
                            }
                        }
                    }
                }
            }
        }
        
        return res;
    }
    
    private boolean isValid(String time) {
        String part1 = time.substring(0, time.indexOf(":"));
        String part2 = time.substring(time.indexOf(":") + 1);
        if (Integer.parseInt(part1) > 24 || Integer.parseInt(part2) >= 60) {
            return false;
        }
        return true;
    }
    
    private int compareTime(String time1, String time2) {
        String part1 = time1.substring(0, time1.indexOf(":"));
        String part2 = time2.substring(0, time2.indexOf(":"));
        int minutes = 0;
        if (Integer.parseInt(part2) < Integer.parseInt(part1)) {
            minutes += (Integer.parseInt(part2) - Integer.parseInt(part1) + 24) * 60;
        } else {
            minutes += (Integer.parseInt(part2) - Integer.parseInt(part1)) * 60;
        } 
        
        part1 = time1.substring(time1.indexOf(":") + 1);
        part2 = time2.substring(time2.indexOf(":") + 1);
        
        if (Integer.parseInt(part2) < Integer.parseInt(part1) && minutes == 0) {
            minutes = 24 * 60 + (Integer.parseInt(part2) - Integer.parseInt(part1));
        } else {
            minutes += (Integer.parseInt(part2) - Integer.parseInt(part1));
        } 
        return minutes;
    }
}
