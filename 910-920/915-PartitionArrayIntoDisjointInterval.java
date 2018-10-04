/*
Given an array A, partition it into two (contiguous) subarrays left and right so that:

Every element in left is less than or equal to every element in right.
left and right are non-empty.
left has the smallest possible size.
Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.

 

Example 1:

Input: [5,0,3,8,6]
Output: 3
Explanation: left = [5,0,3], right = [8,6]
Example 2:

Input: [1,1,1,0,6,12]
Output: 4
Explanation: left = [1,1,1,0], right = [6,12]
 

Note:

2 <= A.length <= 30000
0 <= A[i] <= 10^6
It is guaranteed there is at least one way to partition A as described.

*/

class Solution {
    public int partitionDisjoint(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int[] leftMax = new int[A.length];
        leftMax[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            leftMax[i] = Math.max(A[i], leftMax[i - 1]);
        }
        
        int rightMin = Integer.MAX_VALUE;
        int length = A.length;
        for (int i = A.length - 1; i >= 1; i--) {
            rightMin = Math.min(rightMin, A[i]);
            if (rightMin >= leftMax[i - 1]) {
                length = Math.min(length, i);
            }
        }
        
        return length;
    }
}

// space optimzation
    public int partitionDisjoint(int[] A) {
        int num=A[0];
        int size=0;
        int max=num;
        for(int i=1;i<A.length;i++)
        {
            max=Math.max(max,A[i]);
            if(A[i]<num)
            {
                num=max;
                size=i;
            }
        }
        return size+1;
    }
