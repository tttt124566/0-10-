/*
Given a picture consisting of black and white pixels, find the number of black lonely pixels.

The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't have any other black pixels.

Example:
Input: 
[['W', 'W', 'B'],
 ['W', 'B', 'W'],
 ['B', 'W', 'W']]

Output: 3
Explanation: All the three 'B's are black lonely pixels.
Note:
The range of width and height of the input 2D array is [1,500].
*/
class Solution {
    public int findLonelyPixel(char[][] picture) {
        int[] rowMap = new int[picture.length];
        int[] colMap = new int[picture[0].length];
        int count = 0;
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[i].length; j++) {
                if (picture[i][j] == 'B') {
                    rowMap[i]++;
                    colMap[j]++;
                }
            }
        }
        
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[i].length; j++) {
                if (picture[i][j] == 'B' && rowMap[i] == 1 && colMap[j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }
}
