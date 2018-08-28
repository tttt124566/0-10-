/*
Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true
*/

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> cmap = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            cmap.put(magazine.charAt(i), cmap.getOrDefault(magazine.charAt(i), 0) + 1);
        }
        
        for (int i = 0; i < ransomNote.length(); i++) {
            if (!cmap.containsKey(ransomNote.charAt(i))) {
                return false;
            }
            cmap.put(ransomNote.charAt(i), cmap.get(ransomNote.charAt(i)) - 1);
            if (cmap.get(ransomNote.charAt(i)) < 0) {
                return false;
            }
        }
        
        return true;
    }
}
