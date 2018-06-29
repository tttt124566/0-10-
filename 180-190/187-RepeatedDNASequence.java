/*
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

Example:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"

Output: ["AAAAACCCCC", "CCCCCAAAAA"]
*/

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> list = new ArrayList<String>();
        char[] map = new char[26];
        map['A' - 'A'] = 0;
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;
        Set<Integer> words = new HashSet<Integer>();
        Set<Integer> doubleWords = new HashSet<Integer>();
        for (int i = 0; i < s.length() - 9; i++) {
            int sequence = 0;
            for (int j = i; j < i + 10; j++) {
                sequence = sequence << 1;
                sequence += map[s.charAt(i) - 'A'];
            }
            if (words.contains(sequence) && !doubleWords.contains(sequence)) {
                doubleWords.add(sequence);
                list.add(s.substring(i, i + 10));
            }
            words.add(sequence);
        }
        return list;
    }
}
