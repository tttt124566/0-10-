/*
There's a tree, a squirrel, and several nuts. Positions are represented by the cells in a 2D grid. Your goal is to find the minimal distance for the squirrel to collect all the nuts and put them under the tree one by one. The squirrel can only take at most one nut at one time and can move in four directions - up, down, left and right, to the adjacent cell. The distance is represented by the number of moves.
Example 1:
Input: 
Height : 5
Width : 7
Tree position : [2,2]
Squirrel : [4,4]
Nuts : [[3,0], [2,5]]
Output: 12
Explanation:

Note:
All given positions won't overlap.
The squirrel can take at most one nut at one time.
The given positions of nuts have no order.
Height and width are positive integers. 3 <= height * width <= 10,000.
The given positions contain at least one nut, only one tree and one squirrel.

*/

class Solution {
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int n = nuts.length;
        int[] nutToSquirrel = new int[n];
        int smallestNutToSquirrel = Integer.MAX_VALUE;
        int[] nutToTree = new int[n];
        int sum = 0;
        for (int i = 0; i < nuts.length; i++) {
            nutToTree[i] = Math.abs(nuts[i][0] - tree[0]) + Math.abs(nuts[i][1] - tree[1]);
            sum += 2 * nutToTree[i];
            nutToSquirrel[i] = Math.abs(nuts[i][0] - squirrel[0]) + Math.abs(nuts[i][1] - squirrel[1]);
        }
        
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nuts.length; i++) {
            res = Math.min(res, sum + nutToSquirrel[i] - nutToTree[i]);
        }
        
        return res;
    }
}
