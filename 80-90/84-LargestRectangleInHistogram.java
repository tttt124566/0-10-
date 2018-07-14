/*Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
https://leetcode.com/static/images/problemset/histogram.png
 


The largest rectangle is shown in the shaded area, which has area = 10 unit.

 

Example:

Input: [2,1,5,6,2,3]
Output: 10

*/

class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int largestArea = 0;
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[i] <= heights[stack.peek()]) {
                int height = heights[stack.peek()];
                stack.pop();
                largestArea = Math.max(largestArea, height * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        
        while(stack.size() > 1) {
            int right = stack.peek();
            stack.pop();
            largestArea = Math.max(largestArea, heights[right] * (heights.length - 1 - stack.peek()));
        }
        
        return largestArea;
    }
}

