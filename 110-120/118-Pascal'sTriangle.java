/*
Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.


In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

*/

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (numRows == 0) {
            return list;
        }
        
        List<Integer> currentRow = new ArrayList<>();
        currentRow.add(1);
        list.add(currentRow);
        for (int i = 1; i < numRows; i++) {
            List<Integer> nextRow = new ArrayList<Integer>();
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {
                    nextRow.add(currentRow.get(0));
                } else if (j == i) {
                    nextRow.add(currentRow.get(j - 1));
                } else {
                    nextRow.add(currentRow.get(j - 1) + currentRow.get(j));
                }
            }
            currentRow = nextRow;
            list.add(currentRow);
        }
        
        return list;
    }
}
