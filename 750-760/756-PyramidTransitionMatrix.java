/*
We are stacking blocks to form a pyramid. Each block has a color which is a one letter string, like `'Z'`.

For every block of color `C` we place not in the bottom row, we are placing it on top of a left block of color `A` and right block of color `B`. We are allowed to place the block there only if `(A, B, C)` is an allowed triple.

We start with a bottom row of bottom, represented as a single string. We also start with a list of allowed triples allowed. Each allowed triple is represented as a string of length 3.

Return true if we can build the pyramid all the way to the top, otherwise false.

Example 1:
Input: bottom = "XYZ", allowed = ["XYD", "YZE", "DEA", "FFF"]
Output: true
Explanation:
We can stack the pyramid like this:
    A
   / \
  D   E
 / \ / \
X   Y   Z

This works because ('X', 'Y', 'D'), ('Y', 'Z', 'E'), and ('D', 'E', 'A') are allowed triples.
Example 2:
Input: bottom = "XXYX", allowed = ["XXX", "XXY", "XYX", "XYY", "YXZ"]
Output: false
Explanation:
We can't stack the pyramid to the top.
Note that there could be allowed triples (A, B, C) and (A, B, D) with C != D.
Note:
bottom will be a string with length in range [2, 8].
allowed will have length in range [0, 200].
Letters in all strings will be chosen from the set {'A', 'B', 'C', 'D', 'E', 'F', 'G'}.

*/

class Solution {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : allowed) {
            map.putIfAbsent(s.substring(0, 2), new ArrayList<String>());
            map.get(s.substring(0, 2)).add(s.substring(2, 3));
        }
        return pyramidTransitionHelper(bottom, map);
    }
    
    private boolean pyramidTransitionHelper(String bottom, Map<String, List<String>> map) {
        if (bottom.length() == 1) {
            return true;
        }
        
        for (int i = 0; i < bottom.length() - 1; i++) {
            if (!map.containsKey(bottom.substring(i, i + 2))) {
                return false;
            }
        }
        
        List<String> list = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        constructList(bottom, map, sb, 0, list);
        for (String s : list) {
            if (pyramidTransitionHelper(s, map)) {
                return true;
            }
        }
        return false;
    }
    
    private void constructList(String bottom, Map<String, List<String>> map, StringBuilder sb, int start, List<String> list) {
        if (start == bottom.length() - 1) {
            list.add(sb.toString());
            return;
        }
        for (String s : map.get(bottom.substring(start, start + 2))) {
            sb.append(s);
            constructList(bottom, map, sb, start + 1, list);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
}