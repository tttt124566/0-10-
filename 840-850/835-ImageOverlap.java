/*
Two images A and B are given, represented as binary, square matrices of the same size.  (A binary matrix has only 0s and 1s as values.)

We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on top of the other image.  After, the overlap of this translation is the number of positions that have a 1 in both images.

(Note also that a translation does not include any kind of rotation.)

What is the largest possible overlap?

Example 1:

Input: A = [[1,1,0],
            [0,1,0],
            [0,1,0]]
       B = [[0,0,0],
            [0,1,1],
            [0,0,1]]
Output: 3
Explanation: We slide A to right by 1 unit and down by 1 unit.
Notes: 

1 <= A.length = A[0].length = B.length = B[0].length <= 30
0 <= A[i][j], B[i][j] <= 1
*/

class Solution {
    public int largestOverlap(int[][] A, int[][] B) {
        List<Integer> AList = new ArrayList<Integer>();
        List<Integer> BList = new ArrayList<Integer>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] == 1) {
                    AList.add(100 * i + j); // why 100 ? because 1 <= A.length = A[0].length = B.length = B[0].length <= 30
                }
            }
        }
        
        for(int i = 0; i < B.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                if (B[i][j] == 1) {
                    BList.add(100 * i + j);
                }
            }
        }
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < AList.size(); i++) {
            for (int j = 0; j < BList.size(); j++) {
                // Be careful, access List value use get.
                map.put(BList.get(j) - AList.get(i), map.getOrDefault(BList.get(j) - AList.get(i), 0) + 1);
            }
        }
        
        int maxOverlap = 0;
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) { // iterate through map entries.
            maxOverlap = Math.max(maxOverlap, entry.getValue());
        }
        return maxOverlap;
    }
}
