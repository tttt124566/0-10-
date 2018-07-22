/*
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

Example:

Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Output: 
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
*/


class Solution {
    
    private String getHash(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i + 1) - s.charAt(i) < 0) {
                int diff = s.charAt(i + 1) - s.charAt(i) + 26;
                sb.append(diff + ",");
            } else {
                int diff = s.charAt(i + 1) - s.charAt(i);
                sb.append(diff + ",");
            }
        }
        return sb.toString();
    }
    
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strings) {
            map.putIfAbsent(getHash(s), new ArrayList<String>());
            map.get(getHash(s)).add(s);
        }
        
        List<List<String>> res = new ArrayList<List<String>>();
        for (String key : map.keySet()) {
            res.add(map.get(key));
        }
        
        return res;
    }
}
