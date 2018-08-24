/*
Write a function to generate the generalized abbreviations of a word. 

Note: The order of the output does not matter.

Example:

Input: "word"
Output:
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 

*/

class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        generateAbbreviationsHelper(res, "", 0, word, false);
        return res;
    }
    
    private void generateAbbreviationsHelper(List<String> res, String s, int i, String word, boolean isLastNumber) {
        if (i == word.length()) {
            res.add(s);
            return;
        }
        
        generateAbbreviationsHelper(res, s + word.charAt(i), i + 1, word, false);
        if (isLastNumber == false) {
            for (int j = i + 1; j <= word.length(); j++) {
                generateAbbreviationsHelper(res, s + String.valueOf(j - i), j, word, true);
            }
        }
    }
}
