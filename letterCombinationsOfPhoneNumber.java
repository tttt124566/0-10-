class Solution {
    private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        letterCombinationsHelper(digits, 0, "", res);
        return res;
    }
    
    private void letterCombinationsHelper(String digits, int start, String temp, List<String> res) {
        if (start == digits.length()) {
            res.add(temp);
            return;
        }
        String letters = KEYS[digits.charAt(start) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            letterCombinationsHelper(digits, start + 1, temp + letters.charAt(i), res);
        }
    }
}
