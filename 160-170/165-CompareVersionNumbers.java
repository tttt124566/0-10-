/*
Compare two version numbers version1 and version2.
If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Example 1:

Input: version1 = "0.1", version2 = "1.1"
Output: -1
Example 2:

Input: version1 = "1.0.1", version2 = "1"
Output: 1
Example 3:

Input: version1 = "7.5.2.4", version2 = "7.5.3"
Output: -1

*/

class Solution {
    public int compareVersion(String version1, String version2) {
        String[] version1Strings = version1.split("\\.");
        String[] version2Strings = version2.split("\\.");

        int i = 0;
        while (i < version1Strings.length || i < version2Strings.length) {
            String v1 = i < version1Strings.length ? version1Strings[i] : "0";
            String v2 = i < version2Strings.length ? version2Strings[i] : "0";
            
            if (Integer.parseInt(v1) < Integer.parseInt(v2)) {
                return -1;
            } else if (Integer.parseInt(v1) > Integer.parseInt(v2)) {
                return 1;
            } else {
                continue;
            }
        }
        return 0;
    }
}
