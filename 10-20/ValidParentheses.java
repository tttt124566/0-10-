class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty() || stack.peek() != map.get(s.charAt(i))) {
                stack.push(s.charAt(i));
            } else {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
