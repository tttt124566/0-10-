/*

Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

Example 1:

Input: 123
Output: "One Hundred Twenty Three"
Example 2:

Input: 12345
Output: "Twelve Thousand Three Hundred Forty Five"
Example 3:

Input: 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Example 4:

Input: 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"

*/

class Solution {
    private String[] lower_100 = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private String[] lower_20 = {"One", "Two", "Three", "Four","Five","Six","Seven","Eight","Nine","Ten", "Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
    
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        
        return int_string(num).substring(1);
    }
    
    private String int_string(int n) {
        if (n >= 1000000000) {
            return int_string(n / 1000000000) + " Billion" + int_string(n - n / 1000000000 * 1000000000);
        } else if (n >= 1000000) {
            return int_string(n / 1000000) + " Million" + int_string(n - n / 1000000 * 1000000);
        } else if (n >= 1000) {
            return int_string(n / 1000) + " Thousand" + int_string(n - n / 1000 * 1000);
        } else if (n >= 100) {
            return int_string(n / 100) + " Hundred" + int_string(n - n / 100 * 100);
        } else if (n >= 20) {
            return " " + lower_100[n / 10 - 2] + int_string(n - n / 10 * 10);
        } else if (n >= 1) {
            return " " + lower_20[n - 1];
        } else {
            return "";
        }
    }
};
