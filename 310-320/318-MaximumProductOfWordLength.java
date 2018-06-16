/*
Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:

Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
Output: 16 
Explanation: The two words can be "abcw", "xtfn".
Example 2:

Input: ["a","ab","abc","d","cd","bcd","abcd"]
Output: 4 
Explanation: The two words can be "ab", "cd".
Example 3:

Input: ["a","aa","aaa","aaaa"]
Output: 0 
Explanation: No such pair of words.
*/
// Note: use bit manipulation to check if two strings have same characters.

class Solution {
    public int maxProduct(String[] words) {
        int[] nums = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (char ch : words[i].toCharArray()) {
                nums[i] = nums[i] | (1 << (ch -'a'));
            }
        }

        int maxProduct = 0;
        for (int i = words.length - 1; i >= 1; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if ((nums[i] & nums[j]) == 0) {
                    maxProduct = Math.max(maxProduct, words[i].length() * words[j].length());
                }
            }
        }
        return maxProduct;
    }
}
