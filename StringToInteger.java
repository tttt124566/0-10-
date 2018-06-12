class Solution {
    public int myAtoi(String str) {
        int i = 0;
        while (i < str.length() && str.charAt(i) == ' ') {
            i++;
        }
        int sign = 1;
        if (i < str.length() && (str.charAt(i) == '+' || str.charAt(i) == '-')) {
            sign = str.charAt(i) == '-' ? -1 : 1;
            i++;
        }
        int num = 0;
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            if (num > Integer.MAX_VALUE / 10 || num == Integer.MAX_VALUE / 10 && str.charAt(i) > '7') {
                if (sign == 1) {
                    num = Integer.MAX_VALUE;
                } else {
                    num = Integer.MIN_VALUE;
                }
                break;
            } 
            num = num * 10 + str.charAt(i) - '0';
            i++;
        }
        return num * sign;
    }
}
