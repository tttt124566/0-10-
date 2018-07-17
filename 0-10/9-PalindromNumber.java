/*
Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

Example 1:

Input: 121
Output: true
Example 2:

Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:

Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
*/

class Solution {
    public boolean isPalindrome(int x) {
         if (x < 0)
            return false;
        int m = 1;
        // avoid overflows.
        // et 100001 
        while (m <= x / 10)
        {
            m = m*10;
        }
        System.out.println(m);
        while (x != 0)
        {
            int a = x/m;
            int b = x%10;
            if (a != b)
                return false;
            x = (x - a*m - b) / 10;
            m = m/100;
        }
        return true;
    }
}
