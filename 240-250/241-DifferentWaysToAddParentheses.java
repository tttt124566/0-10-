/*
Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.

Example 1:

Input: "2-1-1"
Output: [0, 2]
Explanation: 
((2-1)-1) = 0 
(2-(1-1)) = 2
Example 2:

Input: "2*3-4*5"
Output: [-34, -14, -10, -10, 10]
Explanation: 
(2*(3-(4*5))) = -34 
((2*3)-(4*5)) = -14 
((2*(3-4))*5) = -10 
(2*((3-4)*5)) = -10 
(((2*3)-4)*5) = 10
*/

class Solution {
    private Map<String, List<Integer>> map = new HashMap<>();
    
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ret = new LinkedList<Integer>();
        for (int i=0; i<input.length(); i++) {
           if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
               String part1 = input.substring(0, i);
               String part2 = input.substring(i + 1);
               List<Integer> ret1 = map.containsKey(part1) ? map.get(part1) : diffWaysToCompute(part1);
               List<Integer> ret2 = map.containsKey(part2) ? map.get(part2) : diffWaysToCompute(part2);
               for (Integer a1 : ret1) {
                   for (Integer a2 : ret2) {
                       if (input.charAt(i) == '+') {
                           ret.add(a1 + a2);
                       } else if (input.charAt(i) == '-') {
                           ret.add(a1 - a2);
                       } else if (input.charAt(i) == '*') {
                           ret.add(a1 * a2);
                       }
                   }
               }
           }
        }
        if (ret.size() == 0) {
            ret.add(Integer.valueOf(input));
        }
        map.put(input, ret);
        return ret;
    }
}
