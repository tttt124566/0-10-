class Solution {
    public void reverseWords(char[] str) {
        reverse(str, 0, str.length - 1);
        int i = 0;
        while (i < str.length) {
            int j = i;
            while (j < str.length && str[j] != ' ') {
                j++;
            }
            reverse(str, i, j - 1);
            i = j + 1;
        }
    }
    
    private void reverse(char[] str, int i, int j) {
        while (i < j) {
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
            i++;
            j--;
        }
    }
}
