/*
An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
     ↓
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
     ↓   ↓    ↓    ↓  ↓    
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
     ↓   ↓    ↓
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example:

Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true
*/
class ValidWordAbbr {

    private Map<String, Set<String>> abbreviationMap;
    public ValidWordAbbr(String[] dictionary) {
        abbreviationMap = new HashMap<>();
        for (String s : dictionary) {
            abbreviationMap.putIfAbsent(getAbbreviation(s), new HashSet<String>());
            abbreviationMap.get(getAbbreviation(s)).add(s);
        }
    }
    
    public boolean isUnique(String word) {
        String abbreviation = getAbbreviation(word);
        if (!abbreviationMap.containsKey(abbreviation) || 
            abbreviationMap.get(abbreviation).contains(word) &&
            abbreviationMap.get(abbreviation).size() == 1
        ) {
            return true;
        }
        return false;
    }
    
    private String getAbbreviation(String s) {
        if (s.length() < 3) {
            return s;
        }
        return s.charAt(0) + String.valueOf(s.length() - 2) + s.charAt(s.length() - 1);
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */
